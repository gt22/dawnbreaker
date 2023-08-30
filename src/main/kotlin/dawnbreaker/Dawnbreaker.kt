package dawnbreaker

import dawnbreaker.data.raw.Element
import dawnbreaker.data.raw.Mod
import dawnbreaker.data.raw.Recipe
import dawnbreaker.dsl.mod
import dawnbreaker.locale.Locale
import java.nio.file.*


lateinit var vanilla: Mod

const val descriptionName = "desc"
const val requirementsName = "reqs"


fun loadVanilla(from: Path) {
    vanilla = Mod.loadVanilla(from)
}

fun convert() {
    val base = "bhcontent"
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

data class Commitment(val skill: Element, val wisdom: String, val soulpart: Element)
data class Workstation(var id: String, val aspects: List<String>, val evolves: String)
fun main() {
    convert()
/*    loadVanilla(Paths.get("bhcontent_norm"))
    val reading = powers.map { "reading.$it" }
    val bookmem = vanilla.elements.asSequence().filter { it.inherits == "_book" }
        .flatMap { b ->
            val mem = reading.firstNotNullOfOrNull { r -> b.xtriggers[r] }?.first()?.id ?: return@flatMap emptySequence()
            sequenceOf(b to vanilla.lookup<Element>(mem)!!)
        }
        .filter { (_, m) -> "numen" !in m.id }
        .toList()

    val validMemories = bookmem.map { (_, m) -> m }.toSet()
    val mod = mod("bookmem") {
        synopsis {
            name = "Book memory labels"
            author = "Frgm"
            version = "1.0.0"
            description = "Adds aspect to each book, labelling a memory that can be obtained by reading it"
        }
        source("memaspects") {
            aspects {
                validMemories.forEach {  mem ->
                    aspect("bookmem.${mem.id}") {
                        icon = mem.id
                        label = "Produces memory: ${mem.label}"
                        description = "Reading this book produces a ${mem.label}"
                    }
                }
            }
        }
        source("tomes") {
            elements {
                bookmem.forEach { (book, mem) ->
                    element(book.id) {
                        aspects {
                            add {
                                "bookmem.${mem.id}" of 1
                            }
                        }
                    }
                }
            }
        }
    }
    mod.saveTo(Paths.get("bookmem"))
//    val commitments = vanilla.elements.asSequence()
//        .filter { "skill" in it.aspects }
//        .flatMap {
//            it.aspects.keys.filter { a -> a.startsWith("w.") }.map { w ->
//                val wisdom = w.removePrefix("w.")
//                val recipe = vanilla.lookup<Recipe>("commit.${wisdom.substring(0..2)}.${it.id}")!!
//                val soulpart = vanilla.lookup<Element>(recipe.effects.keys.first())!!
//                Commitment(it, wisdom, soulpart)
//            }
//        }
//    val workstations = vanilla.verbs.asSequence().filter {
//        it.slots.size == 5 && it.aspects.keys.any { a -> a.startsWith("e.") }
//    }.map {
//        val aspects = it.slots[0].required.keys.toList()
//        val evolves = it.aspects.keys.first { a -> a.startsWith("e.") }.removePrefix("e.")
//        Workstation(it.id, aspects, evolves)
//    }
//    val upgrades = commitments.map { c ->
//        val validWorkstations = workstations.filter { w ->
//            w.evolves == c.wisdom && c.soulpart.aspects.keys.any { a -> a in w.aspects } && c.skill.aspects.keys.any { a -> a in w.aspects }
//        }.map { w -> w.id }.toList()
//        c.skill.id to c.wisdom to c.soulpart.id to validWorkstations
//    }.toList()
//    upgrades.filter { (_, w) -> w.isEmpty() }.forEach { println(it) }
*/
}
