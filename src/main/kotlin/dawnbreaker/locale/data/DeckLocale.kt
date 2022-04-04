package dawnbreaker.locale.data

import dawnbreaker.data.raw.Deck
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class DeckLocale(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var drawmessages: MutableMap<String, String> = mutableMapOf()
) : LocaleData<Deck>