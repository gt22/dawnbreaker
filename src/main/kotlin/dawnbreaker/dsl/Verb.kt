package dawnbreaker.dsl

import dawnbreaker.data.raw.Verb
import dawnbreaker.dsl.internal.*

@ModDsl
class VerbBuilder(override val t: Verb = Verb()) : Builder<Verb> {

    var id by t::id
    var label by t::label
    var description by t::description
    var category by t::category
    val aspects by mprop(t.aspects, t.aspects_add, t.aspects_remove)
    var icon by t::icon
    var spontaneous by t::spontaneous
    var multiple by t::multiple
    val slots by lprop(t.slots, t.slots_append, t.slots_prepend, t.slots_remove)

    var comments by t::comments
    var extends by t::extends
    var deleted by t::deleted

    val xtriggers by mprop(t.xtriggers, t.xtriggers_add, t.xtriggers_remove)
    var audio by t::audio
}