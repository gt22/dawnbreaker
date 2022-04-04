package dawnbreaker.locale.data

import dawnbreaker.data.raw.Ending
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class EndingLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = ""
) : LocaleData<Ending>
