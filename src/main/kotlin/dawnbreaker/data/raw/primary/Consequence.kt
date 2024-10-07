package dawnbreaker.data.raw.primary

import dawnbreaker.data.raw.Data
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Consequence(
    @Required override var id: String = "",
    @SerialName("topath")
    var toPath: String = "",
    var deckeffects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("deckeffects\$add")
    var deckeffects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("deckeffects\$remove")
    var deckeffects_remove: MutableList<String> = mutableListOf(),
) : Data