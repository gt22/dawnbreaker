package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Room
import dawnbreaker.data.raw.Slot
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoomLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var inforecipe: String = "",
    @SerialName("inforecipestartlabel")
    var lockedLabel: String = "",
    @SerialName("inforecipestartdescription")
    var lockedDescription: String = "",
    @SerialName("unlockslot")
    var unlockSlot: SlotLocale? = null,
) : LocaleData<Room> {
    override fun register(base: Room, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        if(unlockSlot == null) {
            unlockSlot = SlotLocale(inforecipe).apply {
                label = this@RoomLocale.lockedLabel
                description = this@RoomLocale.lockedDescription
            }
            inforecipe = ""
            lockedLabel = ""
            lockedDescription = ""
        }
        unlockSlot!!.register(base.unlockSlot!!, data)
    }
}
