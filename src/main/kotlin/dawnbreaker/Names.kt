package dawnbreaker

import java.lang.IllegalArgumentException

val base_principles = listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth")
val principles = listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth", "knock")
val base_principles_sh = listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth", "secrethistories")
val principles_sh = listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth", "knock", "secrethistories")
val powers = listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth", "knock", "rose", "sky", "moon", "scale", "nectar")

fun subvertedBy(p: String): String {
    val i = base_principles.indexOf(p)
    if(i < 0) {
        return p
    }
    val n = base_principles.size
    return base_principles[(i + n - 1) % n]
}

data class Desc(var type: String, var principle: String, var tier: Int) {

    constructor(s: String) : this("", "", -1) {
        type = when {
            s.startsWith("fragment") -> {
                "fragment"
            }
            s.startsWith("influence") -> {
                "influence"
            }
            else -> {
                throw IllegalArgumentException("Indescribable item '$s'")
            }
        }
        val ss = s.removePrefix(type)
        principle = principles_sh.firstOrNull { ss.startsWith(it) } ?: throw IllegalArgumentException("Indescribable item '$s'")
        tier = tier(ss.removePrefix(principle))
    }

    override fun toString() = "$type$principle${tier(tier)}"

}

fun tier(tier: Int) = when(tier) {
    1 -> ""
    2 -> "b"
    3 -> "c"
    4 -> "d"
    5 -> "e"
    6 -> "f"
    7 -> "g"
    else -> throw IllegalArgumentException("Invalid tier $tier")
}

fun tier(tier: String) = when(tier) {
    ""  -> 1
    "b" -> 2
    "c" -> 3
    "d" -> 4
    "e" -> 5
    "f" -> 6
    "g" -> 7
    else -> throw IllegalArgumentException("Invalid tier '$tier'")
}

fun level(level: Int): String {
    return runCatching { tier(level / 2) }.getOrElse { throw IllegalArgumentException("Invalid level $level") }
}

fun String.lore(tier: Int) = "fragment${this}${tier(tier)}"

fun String.influence(tier: Int): String? {
    return if(tier !in listOf(1, 3, 5, 7)) null
    else "influence${this}${tier(tier)}"
}

fun String.loreLevel(level: Int) = "fragment${this}${level(level)}"

fun String.influenceLevel(level: Int): String? {
    return if(level !in listOf(2, 6, 10, 15)) null
    else "influence${this}${level(level)}"
}

fun String.lores() = (1..7).map { lore(it) }

fun String.influences() = (1..7 step 2).map { influence(it)!! }

