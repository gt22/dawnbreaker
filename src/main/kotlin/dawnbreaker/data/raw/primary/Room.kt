package dawnbreaker.data.raw.primary

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.secondary.Slot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    override var id: String = "",
    var label: String = "",
    var description: String = "",
    var aspects: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("unlockessentials")
    var essential: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("unlockrequirements")
    var required: MutableMap<String, Int> = mutableMapOf(),
    @SerialName("unlockforbiddens")
    var forbidden: MutableMap<String, Int> = mutableMapOf(),
    var inforecipe: String = "",
    @SerialName("inforecipestartlabel")
    var lockedLabel: String = "",
    @SerialName("inforecipestartdescription")
    var lockedDescription: String = "",
    @SerialName("unlockslot")
    var unlockSlot: Slot? = null,
    var path: String = "",
    var spherepath: String = "",
    var payloadtype: String = "",
    var sealed: Boolean = false,
    var shrouded: Boolean = false
) : Data {
    init {
        if(unlockSlot == null) {
            unlockSlot = Slot(inforecipe).apply {
                label = this@Room.lockedLabel
                description = this@Room.lockedDescription
                essential = this@Room.essential
                required = this@Room.required
                forbidden = this@Room.forbidden
            }
            inforecipe = ""
            lockedLabel = ""
            lockedDescription = ""
            essential = mutableMapOf()
            required = mutableMapOf()
            forbidden = mutableMapOf()
        }
    }
}
