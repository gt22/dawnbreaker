package dawnbreaker.locale.data

import dawnbreaker.data.raw.Legacy
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class LegacyLocale(
    @Required override var id: String = "",
    var label: String = "",
    var startdescription: String = "",
    var description: String = ""
) : LocaleData<Legacy>
