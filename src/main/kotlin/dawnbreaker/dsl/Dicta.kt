package dawnbreaker.dsl

import dawnbreaker.data.raw.primary.Dicta
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl

@ModDsl
class DictaBuilder(override val t: Dicta = Dicta()) : Builder<Dicta> {

    var id by t::id
    var worldSphereType by t::worldSphereType
    var defaultWorldSpherePath by t::defaultWorldSpherePath
    var masterScene by t::masterScene
    var logoScene by t::logoScene
    var quoteScene by t::quoteScene
    var menuScene by t::menuScene
    var playfieldScene by t::playfieldScene
    var gameOverScene by t::gameOverScene
    var newGameScene by t::newGameScene
    var loadingScene by t::loadingScene
    var noteElementId by t::noteElementId
    var defaultTravelDuration by t::defaultTravelDuration
    var defaultQuickTravelDuration by t::defaultQuickTravelDuration
}