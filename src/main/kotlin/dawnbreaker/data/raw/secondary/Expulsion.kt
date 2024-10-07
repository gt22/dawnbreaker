package dawnbreaker.data.raw.secondary

import kotlinx.serialization.Serializable

@Serializable
data class Expulsion(
    var filter: MutableMap<String, Int> = mutableMapOf(),
    var limit: Int = 0
)