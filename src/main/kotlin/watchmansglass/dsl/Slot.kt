package watchmansglass.dsl

import watchmansglass.data.raw.Slot
import watchmansglass.dsl.internal.*

@ModDsl
class SlotBuilder(override val t: Slot = Slot()) : Builder<Slot> {

    var id by t::id
    var label by t::label
    var description by t::description
    var actionid by t::actionid
    var consumes by t::consumes
    var greedy by t::greedy
    var noanim by t::noanim

    val required by builder(t::required, ::MapBuilder)
    val forbidden by builder(t::forbidden, ::MapBuilder)
}