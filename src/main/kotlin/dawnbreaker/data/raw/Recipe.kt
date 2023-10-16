package dawnbreaker.data.raw

import dawnbreaker.descriptionName
import dawnbreaker.requirementsName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Recipe(
    @Required override var id: String = "",
    var label: String = "",
    var actionid: String = "",
    var startdescription: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var warmup: Int = 0,
    var craftable: Boolean = false,
    var hintonly: Boolean = false,
    var slots: MutableList<Slot> = mutableListOf(),
    var effects: MutableMap<String, String> = mutableMapOf(),
    @SerialName("effects\$add")
    var effects_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("effects\$remove")
    var effects_remove: MutableList<String> = mutableListOf(),
    var aspects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$add")
    var aspects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$remove")
    var aspects_remove: MutableList<String> = mutableListOf(),
    var deckeffects: MutableMap<String, String> = mutableMapOf(),
    @SerialName("deckeffects\$add")
    var deckeffects_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("deckeffects\$remove")
    var deckeffects_remove: MutableList<String> = mutableListOf(),
    @SerialName(requirementsName)
    var requirements: MutableMap<String, String> = mutableMapOf(),
    @SerialName("${requirementsName}\$add")
    var requirements_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("${requirementsName}\$remove")
    var requirements_remove: MutableList<String> = mutableListOf(),
    var extantreqs: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("extantreqs\$add")
    var extantreqs_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("extantreqs\$remove")
    var extantreqs_remove: MutableList<String> = mutableListOf(),
    var tablereqs: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("tablereqs\$add")
    var tablereqs_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("tablereqs\$remove")
    var tablereqs_remove: MutableList<String> = mutableListOf(),
    var fxreqs: MutableMap<String, String> = mutableMapOf(),
    @SerialName("fxreqs\$add")
    var fxreqs_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("fxreqs\$remove")
    var fxreqs_remove: MutableList<String> = mutableListOf(),
    var internaldeck: Deck? = null,
    var linked: MutableList<Recipe> = mutableListOf(),
    @SerialName("linked\$append")
    var linked_append: MutableList<Recipe> = mutableListOf(),
    @SerialName("linked\$prepend")
    var linked_prepend: MutableList<Recipe> = mutableListOf(),
    @SerialName("linked\$remove")
    var linked_remove: MutableList<String> = mutableListOf(),
    var alt: MutableList<Recipe> = mutableListOf(),
    @SerialName("alt\$append")
    var alt_append: MutableList<Recipe> = mutableListOf(),
    @SerialName("alt\$prepend")
    var alt_prepend: MutableList<Recipe> = mutableListOf(),
    @SerialName("alt\$remove")
    var alt_remove: MutableList<String> = mutableListOf(),
    var mutations: MutableList<Mutation> = mutableListOf(),
    @SerialName("mutations\$append")
    var mutations_append: MutableList<Mutation> = mutableListOf(),
    @SerialName("mutations\$prepend")
    var mutations_prepend: MutableList<Mutation> = mutableListOf(),
    @SerialName("mutations\$remove")
    var mutations_remove: MutableList<String> = mutableListOf(),
    var inductions: MutableList<Induces> = mutableListOf(),
    @SerialName("inductions\$append")
    var inductions_append: MutableList<Mutation> = mutableListOf(),
    @SerialName("inductions\$prepend")
    var inductions_prepend: MutableList<Mutation> = mutableListOf(),
    @SerialName("inductions\$remove")
    var inductions_remove: MutableList<String> = mutableListOf(),
    var purge: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("purge\$add")
    var purge_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("purge\$remove")
    var purge_remove: MutableList<String> = mutableListOf(),
    var haltverb: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("haltverb\$add")
    var haltverb_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("haltverb\$remove")
    var haltverb_remove: MutableList<String> = mutableListOf(),
    var deleteverb: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("deleteverb\$add")
    var deleteverb_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("deleteverb\$remove")
    var deleteverb_remove: MutableList<String> = mutableListOf(),
    var burnimage: String = "",
    var portaleffect: String = "",
    var signalimportantloop: Boolean = false,
    @SerialName("signalendingflavour")
    var signalEndingFlavour: String = "",
    var ending: String = "",
    var maxexecutions: Int = 0,
    var comments: String = "",
    var deleted: Boolean = false,
    var inherits: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var fx: MutableMap<String, String> = mutableMapOf(),
    var xpans: MutableMap<String, Int> = mutableMapOf(),
    var outputpath: String = "",
    var notable: Boolean = false,
    var audiooneshot: String = "",
    var achievements: MutableList<String> = mutableListOf(),
    var ambittable: Boolean = true,
    //for linked & alt only
    var chance: Int = 0,
    var additional: Boolean = false,
    var expulsion: Expulsion? = null,
    var challenges: MutableMap<String, String> = mutableMapOf(),
    var topath: String = "",
) : Data {

    private var inherited = false;

    fun inherit(m: Mod) {
        if(inherited) return
        inherited = true
        if(inherits.isEmpty()) return
        val parent = m.lookup<Recipe>(inherits) ?: return
        parent.inherit(m)

        requirements += parent.requirements
        tablereqs += parent.tablereqs
        extantreqs += parent.extantreqs
        effects += parent.effects
        xpans += parent.xpans
        fx += parent.fx
        purge += parent.purge
        haltverb += parent.haltverb
        deleteverb += parent.deleteverb
        deckeffects += parent.deckeffects
        slots += parent.slots
        alt += parent.alt
        linked += parent.linked
        aspects += parent.aspects
        mutations += parent.mutations
        if(warmup <= 0 && parent.warmup > 0) warmup = parent.warmup
        if(parent.signalimportantloop) signalimportantloop = true
        if(parent.craftable) craftable = true
        if(parent.hintonly) hintonly = true
        if(parent.notable) notable = true
        if(actionid.isEmpty()) actionid = parent.actionid
        if(label.length < 2) label = parent.label
        if(startdescription.length < 2) startdescription = parent.startdescription
        if(description.length < 2) description = parent.description
        if(ending.isEmpty()) ending = parent.ending
        if(burnimage.isEmpty()) burnimage = parent.burnimage
        if(portaleffect.isEmpty()) portaleffect = parent.portaleffect
    }

}