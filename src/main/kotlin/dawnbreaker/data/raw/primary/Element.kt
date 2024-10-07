package dawnbreaker.data.raw.primary

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Mod
import dawnbreaker.data.raw.secondary.*
import dawnbreaker.descriptionName
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Element(
    @Required override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var comments: String = "",
    var icon: String = "",
    @SerialName("isaspect")
    var isAspect: Boolean = false,
    @SerialName("ishidden")
    var isHidden: Boolean = false,
    var noartneeded: Boolean = false,
    var aspects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$add")
    var aspects_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("aspects\$remove")
    var aspects_remove: MutableList<String> = mutableListOf(),
    var slots: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$append")
    var slots_append: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$prepend")
    var slots_prepend: MutableList<Slot> = mutableListOf(),
    @SerialName("slots\$remove")
    var slots_remove: MutableList<String> = mutableListOf(),
    var imms: MutableList<Imm> = mutableListOf(),
    @Serializable(XTriggerListSerializer::class)
    var xtriggers: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$add")
    @Serializable(XTriggerListSerializer::class)
    var xtriggers_add: MutableMap<String, MutableList<XTrigger>> = mutableMapOf(),
    @SerialName("xtriggers\$remove")
    var xtriggers_remove: MutableList<String> = mutableListOf(),
    var xexts: MutableMap<String, String> = mutableMapOf(),
    @SerialName("xexts\$add")
    var xexts_add: MutableMap<String, String> = mutableMapOf(),
    @SerialName("xexts\$remove")
    var xexts_remove: MutableList<String> = mutableListOf(),
    var induces: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$append")
    var induces_append: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$prepend")
    var induces_prepend: MutableList<Induces> = mutableListOf(),
    @SerialName("induces\$remove")
    var induces_remove: MutableList<String> = mutableListOf(),
    @SerialName("decayto")
    var decayTo: String = "",
    @SerialName("burnto")
    var burnTo: String = "",
    var sort: String = "",
    var lifetime: Double = .0,
    var resaturate: Boolean = false,
    var unique: Boolean = false,
    var uniquenessgroup: String = "",
    var inherits: String = "",
    var verbicon: String = "",
    var lever: String = "",
    var extends: MutableList<String> = mutableListOf(),
    var deleted: Boolean = false,
    var metafictional: Boolean = false,
    var commute: MutableList<String> = mutableListOf(),
    var manifestationtype: String = "",
    var audio: String = "",
    var achievements: MutableList<String> = mutableListOf(),
    var reverseambittablesdisplay: Boolean = false,
    var ambits: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("ambits\$add")
    var ambits_add: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("ambits\$remove")
    var ambits_remove: MutableList<String> = mutableListOf(),
    var alphalabeloverride: String = ""
) : Data {

    private var inherited: Boolean = false;

    fun inherit(m: Mod) {
        if (inherited) return
        inherited = true
        if (inherits.isEmpty()) return
        val parent = m.lookup<Element>(inherits) ?: return
        parent.inherit(m)

        aspects += parent.aspects
        xtriggers += parent.xtriggers
        imms += parent.imms
        slots += parent.slots
        induces += parent.induces
        manifestationtype = parent.manifestationtype
        uniquenessgroup = parent.uniquenessgroup
        if (lifetime <= 0 && parent.lifetime > 0) lifetime = parent.lifetime
        if (parent.isAspect) isAspect = true
        if (parent.resaturate) resaturate = true
        if (parent.isHidden) isHidden = true
        if (parent.metafictional) metafictional = true
        if (burnTo.isEmpty()) burnTo = parent.burnTo
        if (decayTo.isEmpty()) decayTo = parent.decayTo
        if (label.isEmpty()) label = parent.label
        if (description.isEmpty()) description = parent.description
        if (!reverseambittablesdisplay) reverseambittablesdisplay = parent.reverseambittablesdisplay
        if (alphalabeloverride.isEmpty()) alphalabeloverride = parent.alphalabeloverride
    }
}