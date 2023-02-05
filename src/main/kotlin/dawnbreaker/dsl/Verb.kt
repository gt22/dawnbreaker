package dawnbreaker.dsl

import dawnbreaker.data.raw.Verb
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.builder
import dawnbreaker.dsl.internal.mprop

@ModDsl
class VerbBuilder(override val t: Verb = Verb()) : Builder<Verb> {

    var id by t::id
    var label by t::label
    var description by t::description
    var icon by t::icon
    val slot by builder(t::slot, ::SlotBuilder)

    var comments by t::comments
    var extends by t::extends
    var deleted by t::deleted

    val xtriggers by mprop(t.xtriggers, t.xtriggers_add, t.xtriggers_remove)
}