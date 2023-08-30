package dawnbreaker.locale.data

import dawnbreaker.data.raw.Legacy
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LegacyLocale(
    @Required override var id: String = "",
    var label: String = "",
    var startdescription: String = "",
    @SerialName(descriptionName)
    var description: String = ""
) : LocaleData<Legacy>
