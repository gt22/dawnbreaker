package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.primary.Element
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ElementLocale(
    @Required override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var xexts: MutableMap<String, String> = mutableMapOf(),
    var slots: MutableList<SlotLocale> = mutableListOf()
) : LocaleData<Element> {
    override fun register(base: Element, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        slots.zip(base.slots).forEach { (slot, baseSlot) ->
            if(slot.id != baseSlot.id) {
                throw IllegalStateException("Slot id mismatch at $id: base=${baseSlot.id}, locale=${slot.id}")
            }
            slot.register(baseSlot, data)
        }
    }
}
