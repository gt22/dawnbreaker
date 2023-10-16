package dawnbreaker.locale

import dawnbreaker.data.raw.*
import dawnbreaker.locale.data.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.nio.file.Files
import java.nio.file.Path

interface LocaleData<T : Data> {
    val id: String

    fun register(base: T, data: MutableMap<Data, LocaleData<*>>) {
        data[base] = this
    }
}

@Serializable
data class LocaleSource(
    var elements: MutableList<ElementLocale> = mutableListOf(),
    var recipes: MutableList<RecipeLocale> = mutableListOf(),
    var decks: MutableList<DeckLocale> = mutableListOf(),
    var legacies: MutableList<LegacyLocale> = mutableListOf(),
    var endings: MutableList<EndingLocale> = mutableListOf(),
    var verbs: MutableList<VerbLocale> = mutableListOf(),
    val rooms: MutableList<RoomLocale> = mutableListOf()
)

class Locale(val name: String) {

    private val data: MutableMap<Data, LocaleData<*>> = mutableMapOf()
    private var sources: MutableMap<String, LocaleSource> = mutableMapOf()

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Data> get(data: T): LocaleData<T> {
        return this.data[data] as? LocaleData<T> ?: throw IllegalArgumentException("No locale data for ${data.id}")
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Data> get(id: String): LocaleData<T> {
        return this.data.values.firstOrNull { it.id == id } as? LocaleData<T> ?: throw IllegalArgumentException("No locale data for $id")
    }

    private inline fun <reified T : Data> register(base: Mod, l: List<LocaleData<T>>) {
        l.forEach {
            val d = base.lookup<T>(it.id)
            if(d != null) {
                it.register(d, data)
            }
        }
    }

    fun save(): Map<String, JsonElement> {
        val m = mutableMapOf<String, JsonElement>()
        for ((path, source) in sources) {
            m["loc_$name/$path"] = Mod.cleanup(Json.encodeToJsonElement(source))
        }
        return m
    }

    fun saveTo(p: Path) {
        val m = save()
        p.resolve("loc_$name").toFile().deleteRecursively()
        for ((path, source) in m) {
            val t = p.resolve(path)
            Files.createDirectories(t.parent)
            Files.createFile(t)
            Files.newBufferedWriter(t).use {
                it.write(json.encodeToString(source))
            }
        }
    }

    fun addSource(base: Mod, p: Path, name: String = p.fileName.toString()) {
        addSource(base, read<LocaleSource>(p), name)
    }

    private fun addSource(base: Mod, source: LocaleSource, s: String) {
        sources[s] = source
        register(base, source.elements)
        register(base, source.recipes)
        register(base, source.decks)
        register(base, source.legacies)
        register(base, source.endings)
        register(base, source.verbs)
        register(base, source.rooms)
    }
    companion object {

        private val json = Json {
            isLenient = true
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        private inline fun <reified T> read(p: Path): T {
            val text = Files.newBufferedReader(p).use { it.readText() }.replace("\uFEFF", "")
            if (text.isEmpty()) return json.decodeFromString("{}")

            return try {
                json.decodeFromJsonElement(Mod.normalizeKeys(json.parseToJsonElement(text)))
            } catch (e: SerializationException) {
                System.err.println(p)
                System.err.println(e.localizedMessage)
                json.decodeFromString("{}")
            }
        }

        fun load(name: String, base: Mod, content_p: Path) = Locale(name).apply {
            Files.walk(content_p)
                .filter(Files::isRegularFile)
                .forEach {
                    if (it.toString().endsWith(".json") && !it.toString().contains("settings")) {
                        addSource(base, read<LocaleSource>(it), content_p.relativize(it).toString())
                    }
                }
        }

    }

}