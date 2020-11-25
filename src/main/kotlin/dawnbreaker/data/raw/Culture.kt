package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Culture(
    @Required override var id: String = "",
    var endonym: String = "",
    var exonym: String = "",
    var fontscript: String = "",
    var boldallowed: Boolean = true,
    var uilabels: MutableMap<String, String> = mutableMapOf(),
    @SerialName("uilabels\$add")
    var uilabels_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("uilabels\$remove")
    var uilabels_remove: MutableList<String> = mutableListOf()
) : Data