package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Element(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var comments: String = "",
    var icon: String = "",
    @SerialName("isaspect")
    var isAspect: Boolean = false,
    @SerialName("ishidden")
    var isHidden: Boolean = false,
    var noartneeded: Boolean = false,
    var aspects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$add")
    var aspects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$remove")
    var aspects_remove: MutableList<String> = mutableListOf(),
    var slots: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$append")
    var slots_append: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$prepend")
    var slots_prepend: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$remove")
    var slots_remove: MutableList<String> = mutableListOf(),
    @Serializable(XTriggerListSerializer::class)
    var xtriggers: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$add")
    @Serializable(XTriggerListSerializer::class)
    var xtriggers_add: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$remove")
    var xtriggers_remove: MutableList<String> = mutableListOf(),
    var induces: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$append")
    var induces_append: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$prepend")
    var induces_prepend: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$remove")
    var induces_remove: MutableList<String> = mutableListOf(),
    @SerialName("decayto")
    var decayTo: String = "",
    @SerialName("burnto")
    var burnTo: String = "",
    var lifetime: Int = 0,
    var resaturate: Boolean = false,
    var unique: Boolean = false,
    var uniquenessgroup: String = "",
    var inherits: String = "",
    var verbicon: String = "",
    var lever: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    var metafictional: Boolean = false,
    var commute: MutableList<String> = mutableListOf(),
    var manifestationtype: String = ""
) : Data