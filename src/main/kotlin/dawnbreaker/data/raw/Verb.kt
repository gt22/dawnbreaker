package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Verb(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var icon: String = "",
    var slot: Slot? = null,
    var comments: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    @Serializable(XTriggerListSerializer::class)
    var xtriggers: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$add")
    @Serializable(XTriggerListSerializer::class)
    var xtriggers_add: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$remove")
    var xtriggers_remove: MutableList<String> = mutableListOf(),
) : Data