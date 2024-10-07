package dawnbreaker.data.raw.primary

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Mod
import dawnbreaker.data.raw.secondary.StatusBarElement
import dawnbreaker.descriptionName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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
    var statusbarelements: MutableList<StatusBarElement> = mutableListOf(),
    @SerialName("statusbarelements\$append")
    var statusbarelements_append: MutableList<StatusBarElement> = mutableListOf(),
    @SerialName("statusbarelements\$prepend")
    var statusbarelements_prepend: MutableList<StatusBarElement> = mutableListOf(),
    @SerialName("statusbarelements\$remove")
    var statusbarelements_remove: MutableList<String> = mutableListOf(),
    var comments: String = "",
    var tablecoverimage: String = "",
    var tablesurfaceimage: String = "",
    var tableedgeimage: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    var startup: MutableList<JsonObject> = mutableListOf(), //TODO: Proper decoding
    @SerialName("\$derives")
    var derives: String = ""
) : Data {

    private var inherited: Boolean = false
    fun inherit(m: Mod) {
        if(inherited) return
        inherited = true
        if(derives.isEmpty()) return
        val parent = m.lookup<Legacy>(derives) ?: return
        parent.inherit(m)
        if(label.isEmpty()) label = parent.label
        if(family.isEmpty()) family = parent.family
        if(description.isEmpty()) description = parent.description
        if(startdescription.isEmpty()) startdescription = parent.description
        if(image.isEmpty()) image = parent.image
        if(!newstart) newstart = parent.newstart
        if(fromending.isEmpty()) fromending = parent.fromending
        if(!availableWithoutEndingMatch) availableWithoutEndingMatch = parent.availableWithoutEndingMatch
        if(startingverbid.isEmpty()) startingverbid = parent.startingverbid
        excludesOnEnding += parent.excludesOnEnding
        effects += parent.effects
        statusbarelements += parent.statusbarelements

        if(tablecoverimage.isEmpty()) tablecoverimage = parent.tablecoverimage
        if(tablesurfaceimage.isEmpty()) tablesurfaceimage = parent.tablesurfaceimage
        if(tableedgeimage.isEmpty()) tableedgeimage = parent.tableedgeimage
    }

}