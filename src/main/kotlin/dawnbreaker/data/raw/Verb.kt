package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Verb(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var slot: Slot? = null,
    var comments: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false
) : Data