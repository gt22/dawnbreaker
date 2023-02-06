package dawnbreaker.data.raw

import kotlinx.serialization.Serializable

@Serializable
data class Mutation(
    var filter: String = "",
    var mutate: String = "",
    var level: String = "",
    var additive: Boolean = false
)