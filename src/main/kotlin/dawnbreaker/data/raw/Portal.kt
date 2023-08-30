package dawnbreaker.data.raw

import dawnbreaker.descriptionName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Portal(
    @Required override var id: String = "",
    var icon: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    @SerialName("otherworldid")
    var otherworldId: String = "",
    @SerialName("egressid")
    var egressId: String = "",
    var consequences: MutableList<Consequence> = mutableListOf(),
    @SerialName("consequences\$append")
    var consequences_append: MutableList<Consequence> = mutableListOf(),
    @SerialName("consequences\$prepend")
    var consequences_prepend: MutableList<Consequence> = mutableListOf(),
    @SerialName("consequences\$remove")
    var consequences_remove: MutableList<String> = mutableListOf(),
) : Data