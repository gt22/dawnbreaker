package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Ending(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var image: String = "",
    var flavour: String = "",
    var anim: String = "",
    var achievement: String = "",
    var achievements: MutableList<String> = mutableListOf(),
    var comments: String = ""
) : Data