package dawnbreaker.dsl

import dawnbreaker.data.raw.Deck
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.lprop
import dawnbreaker.dsl.internal.mprop


@ModDsl
class DeckBuilder(override val t: Deck = Deck()) : Builder<Deck> {

    var id by t::id
    var label by t::label
    var forlegacyfamily by t::forlegacyfamily
    var description by t::description
    var comments by t::comments
    var defaultcard by t::defaultcard
    val spec by lprop(t.spec, t.spec_append, t.spec_prepend, t.spec_remove)
    var resetonexhaustion by t::resetonexhaustion
    var draws by t::draws
    var defaultdraws by t::defaultdraws
    val drawmessages by mprop(t.drawmessages, t.drawmessages_add, t.drawmessages_remove)
    val drawmessagesdefault by mprop(t.drawmessages, t.drawmessages_add, t.drawmessages_remove)
    var extends by t::extends
    var deleted by t::deleted
    
}