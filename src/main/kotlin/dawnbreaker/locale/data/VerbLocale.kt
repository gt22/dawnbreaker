package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.primary.Verb
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerbLocale(
    @Required override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var slot: SlotLocale? = null,
    var slots: MutableList<SlotLocale> = mutableListOf()
) : LocaleData<Verb> {

    override fun register(base: Verb, data: MutableMap<Data, LocaleData<*>>) {
        if(slots.size != base.slots.size) {
//            throw IllegalStateException("Invalid slot localization at $id")
            System.err.println("Invalid slot localization at $id")
            return
        }
        super.register(base, data)
        slot?.let { slots.add(0, it) }
        slot = null
        slots.zip(base.slots).forEach { (l, b) -> data[b] = l }
    }
}
