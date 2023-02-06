package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Verb
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class VerbLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var slot: SlotLocale? = null,
    var slots: MutableList<SlotLocale> = mutableListOf()
) : LocaleData<Verb> {
    override fun register(base: Verb, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        slot?.let { slots.add(0, it) }
        slot = null
        if(slots.size != base.slots.size) {
            throw IllegalStateException("Invalid slot localization")
        }
        slots.zip(base.slots).forEach { (l, b) -> data[b] = l }
    }
}
