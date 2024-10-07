package dawnbreaker.locale.data

import dawnbreaker.data.raw.primary.Deck
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeckLocale(
    override var id: String = "",
    var label: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var drawmessages: MutableMap<String, String> = mutableMapOf()
) : LocaleData<Deck>