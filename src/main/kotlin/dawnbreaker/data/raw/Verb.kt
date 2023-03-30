package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Verb(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var category: String = "",
    var icon: String = "",
    var spontaneous: Boolean = false,
    var multiple: Boolean = false,
    private var slot: Slot? = null,
    var slots: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$append")
    var slots_append: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$prepend")
    var slots_prepend: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$remove")
    var slots_remove: MutableList<String> = mutableListOf(),
    var comments: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    var aspects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$add")
    var aspects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$remove")
    var aspects_remove: MutableList<String> = mutableListOf(),
    @Serializable(XTriggerListSerializer::class)
    var xtriggers: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$add")
    @Serializable(XTriggerListSerializer::class)
    var xtriggers_add: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$remove")
    var xtriggers_remove: MutableList<String> = mutableListOf(),
) : Data {
    init {
        slot?.let { slots.add(0, it) }
        slot = null
    }
}