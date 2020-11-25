package dawnbreaker

import dawnbreaker.data.raw.Mod
import java.nio.file.*


lateinit var vanilla: Mod


fun loadVanilla(from: Path) {
    vanilla = Mod.loadVanilla(from)
}