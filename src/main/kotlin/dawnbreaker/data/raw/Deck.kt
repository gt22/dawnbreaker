package dawnbreaker.data.raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deck(
    override var id: String = "",
    var label: String = "",
    var forlegacyfamily: String = "",
    var description: String = "",
    var comments: String = "",
    var defaultcard: String = "",
    var spec: MutableList<String> = mutableListOf(),
    @SerialName("spec\$append")
    var spec_append: MutableList<String> = mutableListOf(),
    @SerialName("spec\$prepend")
    var spec_prepend: MutableList<String> = mutableListOf(),
    @SerialName("spec\$remove")
    var spec_remove: MutableList<String> = mutableListOf(),
    var resetonexhaustion: Boolean = false,
    var draws: Int = 0,
    var defaultdraws: Int = 0,
    var drawmessages: MutableMap<String, String> = mutableMapOf(),
    @SerialName("drawmessages\$add")
    var drawmessages_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("drawmessages\$remove")
    var drawmessages_remove: MutableList<String> = mutableListOf(),
    var drawmessagesdefault: MutableMap<String, String> = mutableMapOf(),
    @SerialName("drawmessagesdefault\$add")
    var drawmessagesdefault_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("drawmessagesdefault\$remove")
    var drawmessagesdefault_remove: MutableList<String> = mutableListOf(),
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false
) : Data