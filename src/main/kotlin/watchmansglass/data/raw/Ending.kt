package watchmansglass.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Ending(
    @Required override var id: String = "",
    var label: String = "",
    var description: String = "",
    var image: String = "",
    var flavour: String = "",
    var anim: String = "",
    var achievement: String = "",
    var comments: String = ""
) : Data