package dawnbreaker.locale.data

import dawnbreaker.data.raw.Slot
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class SlotLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = ""
) : LocaleData<Slot>
