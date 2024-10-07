package dawnbreaker.data.raw.primary

import dawnbreaker.data.raw.Data
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Achievement(
    @Required override var id: String = "",
    @SerialName("iscategory")
    var isCategory: Boolean = false,
    var label: String = "",
    @SerialName("ishidden")
    var isHidden: Boolean = false,
    @SerialName("iconunlocked")
    var iconUnlocked: String = "",
    var category: String = "",
    @SerialName("singledescription")
    var singleDescription: Boolean = false,
    @SerialName("validateonstorefront")
    var validateOnStorefront: Boolean = false,
    @SerialName("descriptionunlocked")
    var descriptionUnlocked: String = ""
) : Data