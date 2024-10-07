package dawnbreaker.data.raw.secondary

import dawnbreaker.data.raw.Data
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Induces(
    @Required override var id: String = "",
    var chance: Int = 0,
    var additional: Boolean = false,
    var expulsion: Expulsion? = null
) : Data