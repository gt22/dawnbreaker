package dawnbreaker.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dicta(
    @Required override var id: String = "",
    @SerialName("worldspheretype")
    var worldSphereType: String = "",
    @SerialName("alternativedefaultworldspherepaths")
    var alternativeDefaultWorldSpherePaths: MutableList<String> = mutableListOf(),
    @SerialName("defaultcardback")
    var defaultCardBack: String = "",
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
    @SerialName("defaultlongtravelduration")
    var defaultLongTravelDuration: String = "",
    @SerialName("defaultgamespeed")
    var defaultGameSpeed: String = "",
    @SerialName("storedmanifestation")
    var storedManifestation: String = "",
    @SerialName("storedphyicalmanifestation")
    var storedPhysicalManifestation: String = ""
) : Data