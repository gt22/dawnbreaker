package watchmansglass.dsl

import watchmansglass.data.raw.Slot
import watchmansglass.data.raw.Verb
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.Init
import watchmansglass.dsl.internal.ModDsl
import watchmansglass.dsl.internal.builder

@ModDsl
class VerbBuilder(override val t: Verb = Verb()) : Builder<Verb> {

    var id by t::id
    var label by t::label
    var description by t::description
    val slot by builder(t::slot, ::SlotBuilder)

    var comments by t::comments
    var extends by t::extends
    var deleted by t::deleted
    
}