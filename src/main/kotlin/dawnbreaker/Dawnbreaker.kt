package dawnbreaker

import dawnbreaker.data.raw.Element
import dawnbreaker.data.raw.Mod
import dawnbreaker.data.raw.Recipe
import dawnbreaker.dsl.mod
import dawnbreaker.locale.Locale
import java.nio.file.*


lateinit var vanilla: Mod


fun loadVanilla(from: Path) {
    vanilla = Mod.loadVanilla(from)
}


fun main() {
    val base = "content_boh"
    loadVanilla(Paths.get(base))
    vanilla.saveTo(Paths.get("${base}_norm"), saveAsCore = true)
    Locale.load("en", vanilla, Paths.get("$base/core"))
        .saveTo(Paths.get("${base}_norm"))
    Files.list(Paths.get(base))
        .filter { it.fileName.toString().startsWith("loc_") }
        .forEach {
            Locale.load(it.fileName.toString().removePrefix("loc_"), vanilla, it)
                .saveTo(Paths.get("${base}_norm"))
        }
}