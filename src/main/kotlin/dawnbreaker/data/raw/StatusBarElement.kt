package dawnbreaker.data.raw

import kotlinx.serialization.Serializable

@Serializable
data class StatusBarElement(
    var ids: MutableList<String> = mutableListOf(),
    var format: MutableList<String> = mutableListOf(),
    var styles: MutableList<String> = mutableListOf()
)
