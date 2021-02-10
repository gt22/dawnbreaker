package dawnbreaker.data.raw

import com.sun.xml.internal.ws.developer.Serialization
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.lang.IllegalArgumentException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

fun copyFolder(source: Path, target: Path, vararg options: CopyOption?) {
    Files.walkFileTree(source, object : SimpleFileVisitor<Path>() {
        override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
            Files.createDirectories(target.resolve(source.relativize(dir)))
            return FileVisitResult.CONTINUE
        }

        override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
            Files.copy(file, target.resolve(source.relativize(file)), *options)
            return FileVisitResult.CONTINUE
        }
    })
}

interface Data {

    val id: String

}


@Serializable
data class Source(
    @Transient var mod: Mod? = null,
    @Transient override var id: String = "",
    var elements: MutableList<Element> = mutableListOf(),
    var recipes: MutableList<Recipe> = mutableListOf(),
    var decks: MutableList<Deck> = mutableListOf(),
    var legacies: MutableList<Legacy> = mutableListOf(),
    var endings: MutableList<Ending> = mutableListOf(),
    var verbs: MutableList<Verb> = mutableListOf(),
    var cultures: MutableList<Culture> = mutableListOf(),
) : Data {

    fun prefix(s: String) = mod?.prefix(s) ?: s

    inline fun <reified T : Data> exists(id: String): Boolean = sequenceOf(
        elements,
        recipes,
        decks,
        legacies,
        endings,
        verbs,
        cultures
    )
        .flatten()
        .filterIsInstance<T>()
        .filterNotNull()
        .filter { it.id == id }
        .any()

    fun <T : Data> lookup(id: String, from: Iterable<T>) = from.firstOrNull { it.id == id }

    inline fun <reified T : Data> lookup(id: String): T? {
        val from: MutableList<T> = when (T::class) {
            Element::class -> elements
            Recipe::class -> recipes
            Deck::class -> decks
            Legacy::class -> legacies
            Ending::class -> endings
            Verb::class -> verbs
            Culture::class -> cultures
            else -> throw IllegalArgumentException("Invalid data type for lookup")
        } as MutableList<T>
        return lookup(id, from)
    }
}

data class Mod(
    val prefix_v: String = "",
    var synopsis: Synopsis = Synopsis(),
    var sources: MutableMap<String, Source> = mutableMapOf(),
    var images: Path? = null,
    var additionalfiles: Path? = null
) {

    //Accessors

    val elements: List<Element>
        get() = sources.flatMap { it.value.elements }
    val recipes: List<Recipe>
        get() = sources.flatMap { it.value.recipes }
    val decks: List<Deck>
        get() = sources.flatMap { it.value.decks }
    val legacies: List<Legacy>
        get() = sources.flatMap { it.value.legacies }
    val endings: List<Ending>
        get() = sources.flatMap { it.value.endings }
    val verbs: List<Verb>
        get() = sources.flatMap { it.value.verbs }
    val cultures: List<Culture>
        get() = sources.flatMap { it.value.cultures }

    //End accessors

    //Automatically enabled if loaded as vanilla
    var checkOnSearch = false

    fun prefix(s: String) = prefix_v + s

    inline fun <reified T : Data> exists(id: String): Boolean =
        prefix(id).let { sources.any { (_, v) -> v.exists<T>(it) } }

    inline fun <reified T : Data> find(id: String, check: Boolean = checkOnSearch): String {
        if (check) require(exists<T>(id)) { "${T::class.simpleName} '$id' was not found in mod with prefix '$prefix_v'" }
        return prefix(id)
    }

    inline fun <reified T : Data> lookup(id: String): T? =
        sources.values.map { it.lookup<T>(id) }.firstOrNull { it != null }

    operator fun get(s: String) = find<Data>(s)

    private fun cleanup(json: JsonElement): JsonElement = with(json) {
        when (this) {
            is JsonPrimitive -> {
                return try {
                    JsonPrimitive(content.toInt())
                } catch (e: NumberFormatException) {
                    this
                }
            }
            is JsonObject -> JsonObject(mapValues { (_, v) -> cleanup(v) })
            is JsonArray -> JsonArray(map(::cleanup))
            else -> return this
        }
    }

    fun save(): Map<String, JsonElement> {
        val m = mutableMapOf<String, JsonElement>()
        m["synopsis.json"] = cleanup(Json.encodeToJsonElement(synopsis))
        for ((path, source) in sources) {
            m["content/$path"] = cleanup(Json.encodeToJsonElement(source))
        }
        return m
    }

    fun saveTo(p: Path) {
        val m = save()
        val num = p.resolve("serapeum_catalogue_number.txt")
        val numv = if (Files.exists(num)) {
            Files.newBufferedReader(num).use { it.readText() }
        } else ""
        p.toFile().deleteRecursively()
        for ((path, source) in m) {
            val t = p.resolve(path)
            Files.createDirectories(t.parent)
            Files.createFile(t)
            Files.newBufferedWriter(t).use {
                it.write(json.encodeToString(source))
            }
        }
        if (numv != "") {
            Files.newBufferedWriter(num).use {
                it.write(numv)
            }
        }
        images?.let { copyFolder(it, p.resolve("images")) }
        additionalfiles?.let { copyFolder(it, p) }
    }

    companion object {

        val json = Json {
            isLenient = true
            prettyPrint = true
        }

        fun loadMod(p: Path) = load(p.resolve("content"), p.resolve("synopsis.json"))

        fun loadVanilla(p: Path) = load(p.resolve("core"), null).apply { checkOnSearch = true }

        private fun normalizeKeys(j: JsonElement): JsonElement = when (j) {
            is JsonObject -> {
                JsonObject(
                    j
                    .mapKeys { (k, _) -> k.toLowerCase() }
                    .mapValues { (_, v) -> normalizeKeys(v) }
                )
            }
            is JsonArray -> {
                JsonArray(j.map(::normalizeKeys))
            }
            else -> j
        }

        private inline fun <reified T> read(p: Path): T {
            val text = Files.newBufferedReader(p).use { it.readText() }.replace("\uFEFF", "")
            if (text.isEmpty()) return json.decodeFromString("{}")

            return try {
                json.decodeFromJsonElement(normalizeKeys(json.parseToJsonElement(text)))
            } catch (e: SerializationException) {
                System.err.println(p)
                System.err.println(e.localizedMessage)
                json.decodeFromString("{}")
            }
        }

        fun load(content_p: Path, synopsis_p: Path? = null) = Mod().apply {
            if (synopsis_p != null) {
                synopsis = read(synopsis_p)
            }
            Files.walk(content_p)
                .filter(Files::isRegularFile)
                .forEach {
                    if (!it.toString().contains("settings")) {
                        val name = content_p.relativize(it).toString()
                        sources[name] = read(it)
                    }
                }
        }

    }

}