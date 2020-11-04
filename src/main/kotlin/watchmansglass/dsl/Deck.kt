package watchmansglass.dsl

import watchmansglass.data.raw.Deck
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.ModDsl
import watchmansglass.dsl.internal.lprop
import watchmansglass.dsl.internal.mprop


@ModDsl
class DeckBuilder(override val t: Deck = Deck()) : Builder<Deck> {

    var id by t::id
    var label by t::label
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