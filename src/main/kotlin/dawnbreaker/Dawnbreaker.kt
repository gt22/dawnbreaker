package dawnbreaker

import dawnbreaker.data.raw.Element
import dawnbreaker.data.raw.Mod
import dawnbreaker.data.raw.Recipe
import dawnbreaker.dsl.mod
import java.nio.file.*


lateinit var vanilla: Mod


fun loadVanilla(from: Path) {
    vanilla = Mod.loadVanilla(from)
}


fun main() {
    loadVanilla(Paths.get("content"))
    vanilla.saveTo(Paths.get("content_norm"))
}