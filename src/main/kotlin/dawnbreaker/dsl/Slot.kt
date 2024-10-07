package dawnbreaker.dsl

import dawnbreaker.data.raw.secondary.Slot
import dawnbreaker.dsl.internal.*

@ModDsl
class SlotBuilder(override val t: Slot = Slot()) : Builder<Slot> {

    var id by t::id
    var label by t::label
    var description by t::description
    var actionid by t::actionid
    var consumes by t::consumes
    var greedy by t::greedy
    var noanim by t::noanim

    val essential by builder(t::essential, ::MapBuilder)
    val required by builder(t::required, ::MapBuilder)
    val forbidden by builder(t::forbidden, ::MapBuilder)
    val ifaspectspresent by builder(t::ifaspectspresent, ::MapBuilder)
}