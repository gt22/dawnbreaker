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
    loadVanilla(Paths.get("content"))
    vanilla.saveTo(Paths.get("content_norm"), saveAsCore = true)
    Locale.load("en", vanilla, Paths.get("content/core"))
        .saveTo(Paths.get("content_norm"))
    Files.list(Paths.get("content"))
        .filter { it.fileName.toString().startsWith("loc_") }
        .forEach {
            Locale.load(it.fileName.toString().removePrefix("loc_"), vanilla, it)
                .saveTo(Paths.get("content_norm"))
        }
}