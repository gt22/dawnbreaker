package dawnbreaker.locale.data

import dawnbreaker.data.raw.primary.Ending
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EndingLocale(
    @Required override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = ""
) : LocaleData<Ending>
