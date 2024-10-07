package dawnbreaker.data.raw.secondary

import dawnbreaker.data.raw.Data
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Slot(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var actionid: String = "",
    var consumes: Boolean = false,
    var greedy: Boolean = false,
    var noanim: Boolean = false,
    var essential: MutableMap<String, Int> = mutableMapOf(),
    var required: MutableMap<String, Int> = mutableMapOf(),
    var forbidden: MutableMap<String, Int> = mutableMapOf(),
    var ifaspectspresent: MutableMap<String, Int> = mutableMapOf(),
    var frompath: String = ""
) : Data