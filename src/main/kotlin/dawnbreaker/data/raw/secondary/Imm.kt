package dawnbreaker.data.raw.secondary

import kotlinx.serialization.Serializable

@Serializable
data class Imm(
    var reqs: MutableMap<String, String> = mutableMapOf(),
    var effects: MutableMap<String, String> = mutableMapOf()
)
