package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Element
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class ElementLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var slots: MutableList<SlotLocale> = mutableListOf()
) : LocaleData<Element> {
    override fun register(base: Element, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        slots.zip(base.slots).forEach { (slot, baseSlot) ->
            if(slot.id != baseSlot.id) {
                throw IllegalStateException("Slot id mismatch")
            }
            slot.register(baseSlot, data)
        }
    }
}
