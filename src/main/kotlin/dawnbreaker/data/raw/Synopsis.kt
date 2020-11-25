package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Synopsis(
    @Required var name: String = "",
    var author: String = "",
    var version: String = "",
    var description: String = "",
    var description_long: String = "",
    var dependencies: MutableList<String> = mutableListOf()
)