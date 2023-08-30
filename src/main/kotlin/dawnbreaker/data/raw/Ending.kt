package dawnbreaker.data.raw

import dawnbreaker.descriptionName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Ending(
    @Required override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var image: String = "",
    var flavour: String = "",
    var anim: String = "",
    var achievement: String = "",
    var achievements: MutableList<String> = mutableListOf(),
    var comments: String = ""
) : Data