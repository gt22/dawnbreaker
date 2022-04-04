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
    var slot: SlotLocale? = null
) : LocaleData<Verb> {
    override fun register(base: Verb, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        if(slot != null) {
            if(base.slot == null) {
                throw IllegalStateException("Localization for missing slot")
            }
            data[base.slot!!] = slot!!
        }
    }
}
