package dawnbreaker.dsl

import dawnbreaker.data.raw.primary.Legacy
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.lprop
import dawnbreaker.dsl.internal.mprop

class LegacyBuilder(override val t: Legacy = Legacy()) : Builder<Legacy> {

    var id by t::id
    var label by t::label
    var family by t::family
    var description by t::description
    var startdescription by t::startdescription
    var image by t::image
    var newstart by t::newstart
    var fromending by t::fromending
    var availableWithoutEndingMatch by t::availableWithoutEndingMatch

    val excludesOnEnding by lprop(t.excludesOnEnding, t.excludesOnEnding_add, t.excludesOnEnding_add, t.excludesOnEnding_remove)

    var startingverbid by t::startingverbid

    val effects by mprop(t.effects, t.effects_add, t.effects_remove)

    val statusbarelements by lprop(t.statusbarelements, t.statusbarelements_append, t.statusbarelements_prepend, t.statusbarelements_remove)

    var comments by t::comments
    var tablecoverimage by t::tablecoverimage
    var tablesurfaceimage by t::tablesurfaceimage
    var tableedgeimage by t::tableedgeimage
    var extends by t::extends
    var deleted by t::deleted
    
}