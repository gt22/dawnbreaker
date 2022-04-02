package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dicta(
    @Required override var id: String = "",
    @SerialName("worldspheretype")
    var worldSphereType: String = "",
    @SerialName("defaultworldspherepath")
    var defaultWorldSpherePath: String = "",
    @SerialName("masterscene")
    var masterScene: String = "",
    @SerialName("logoscene")
    var logoScene: String = "",
    @SerialName("quotescene")
    var quoteScene: String = "",
    @SerialName("menuscene")
    var menuScene: String = "",
    @SerialName("playfieldscene")
    var playfieldScene: String = "",
    @SerialName("gameoverscene")
    var gameOverScene: String = "",
    @SerialName("newgamescene")
    var newGameScene: String = "",
    @SerialName("noteelementid")
    var noteElementId: String = "",
    @SerialName("defaulttravelduration")
    var defaultTravelDuration: String = "",
    @SerialName("defaultquicktravelduration")
    var defaultQuickTravelDuration: String = "",
) : Data