package dawnbreaker

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction
import java.nio.charset.MalformedInputException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.inputStream

fun normalizeKeys(j: JsonElement): JsonElement = when (j) {
    is JsonObject -> {
        JsonObject(
            j
                .mapKeys { (k, _) -> k.lowercase() }
                .mapValues { (_, v) -> normalizeKeys(v) }
        )
    }

    is JsonArray -> {
        JsonArray(j.map(::normalizeKeys))
    }

    else -> j
}

fun readEnc(p: Path, c: Charset): String? {
    return try {
        BufferedReader(InputStreamReader(Files.newInputStream(p), c.newDecoder())).use { it.readText() }
            .replace("\uFEFF", "")
    } catch (e: MalformedInputException) {
        null
    }
}

inline fun <reified T> Json.read(p: Path): T {
    val text = readEnc(p, StandardCharsets.UTF_8) ?: readEnc(p, StandardCharsets.UTF_16LE)
    ?: throw IllegalArgumentException("Unable to read in UTF8 or UTF16")
    if (text.isEmpty()) return decodeFromString("{}")

    return try {
        decodeFromJsonElement(normalizeKeys(parseToJsonElement(text)))
    } catch (e: SerializationException) {
        System.err.println(p)
        System.err.println(e.localizedMessage)
        decodeFromString("{}")
    }
}