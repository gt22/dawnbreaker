package dawnbreaker.data.raw

import dawnbreaker.descriptionName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.JsonObject

@Serializable
data class Legacy(
    @Required override var id: String = "",
    var label: String = "",
    var family: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var startdescription: String = "",
    var image: String = "",
    var newstart: Boolean = false,
    var fromending: String = "",
    @SerialName("availablewithoutendingmatch")
    var availableWithoutEndingMatch: Boolean = true,
    @SerialName("excludesonending")
    var excludesOnEnding: MutableList<String> = mutableListOf(),
    @SerialName("excludesonending\$add")
    var excludesOnEnding_add: MutableList<String> = mutableListOf(),
    @SerialName("excludesonending\$remove")
    var excludesOnEnding_remove: MutableList<String> = mutableListOf(),
    var startingverbid: String = "",
    var effects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("effects\$add")
    var effects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("effects\$remove")
    var effects_remove: MutableList<String> = mutableListOf(),
    var statusbarelements: MutableList<String> = mutableListOf(),
    @SerialName("statusbarelements\$append")
    var statusbarelements_append: MutableList<String> = mutableListOf(),
    @SerialName("statusbarelements\$prepend")
    var statusbarelements_prepend: MutableList<String> = mutableListOf(),
    @SerialName("statusbarelements\$remove")
    var statusbarelements_remove: MutableList<String> = mutableListOf(),
    var comments: String = "",
    var tablecoverimage: String = "",
    var tablesurfaceimage: String = "",
    var tableedgeimage: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    var startup: MutableList<JsonObject> = mutableListOf() //TODO: Proper decoding
) : Data