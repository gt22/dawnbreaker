package watchmansglass

import watchmansglass.data.raw.Deck
import watchmansglass.data.raw.Element
import watchmansglass.data.raw.Mod
import watchmansglass.mods.compat.compat
import java.io.IOException
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes


lateinit var vanilla: Mod
lateinit var issues: List<String>

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

fun main() {
    vanilla = Mod.loadVanilla(Paths.get("content_norm"))
    compat().saveTo(Paths.get("C:\\Users\\igore\\AppData\\LocalLow\\Weather Factory\\Cultist Simulator\\mods\\compat"))
    compat().saveTo(Paths.get("compat"))
//    copyFolder(Paths.get("compat.images"),
//        Paths.get("C:\\Users\\igore\\AppData\\LocalLow\\Weather Factory\\Cultist Simulator\\mods\\compat\\images"))
//    val icondest = Paths.get("C:\\Users\\igore\\AppData\\LocalLow\\Weather Factory\\Cultist Simulator\\mods\\compat\\cover.png")
//    if(Files.exists(icondest)) Files.delete(icondest)
//    Files.copy(Paths.get("icon.png"), icondest)
//    seekerInit()
//    seeker.saveTo(Paths.get("/Users/gt22/Library/Application Support/Weather Factory/Cultist Simulator/mods/seeker"))
}

fun makeIssues() {
    for ((n, s) in vanilla.sources) {
        val (type, count) = when {
            s.decks.isNotEmpty() -> "decks" to s.decks.size
            s.elements.isNotEmpty() -> "elements" to s.elements.size
            s.endings.isNotEmpty() -> "endings" to s.endings.size
            s.legacies.isNotEmpty() -> "legacies" to s.legacies.size
            s.recipes.isNotEmpty() -> "recipes" to s.recipes.size
            s.verbs.isNotEmpty() -> "verbs" to s.verbs.size
            else -> "" to 0
        }
        if (type.isNotEmpty()) {
            if (issue(n, type, count)) break
            Thread.sleep(100)
        }
    }
}

fun issue(name: String, type: String, count: Int): Boolean {
    val title = name.substringAfter("/").removeSuffix(".json")
    if (title !in issues) {
        println(title)
//        ProcessBuilder("gh", "issue", "create",
//        "-R", "gt22/house-of-lethe-RU",
//        "--title", title,
//        "--label", type,
//        "--body", "Entries: $count").start()
    }
    return false

}

fun nonUnique(l: Deck): List<String> = l.spec.filter { name ->
    val i = vanilla.sources.flatMap { s -> s.value.elements }.first { e -> e.id == name }
    !i.unique && i.uniquenessgroup.isEmpty()
}
