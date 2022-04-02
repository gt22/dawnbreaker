package dawnbreaker.dsl

import dawnbreaker.data.raw.Consequence
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.mprop

@ModDsl
class ConsequenceBuilder(override var t: Consequence = Consequence()) : Builder<Consequence> {

    var id by t::id
    var toPath by t::toPath
    val deckeffects by mprop(t.deckeffects, t.deckeffects_add, t.deckeffects_remove)

}