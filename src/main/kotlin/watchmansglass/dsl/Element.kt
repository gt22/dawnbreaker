package watchmansglass.dsl

import watchmansglass.data.raw.Element
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.ModDsl
import watchmansglass.dsl.internal.lprop
import watchmansglass.dsl.internal.mprop

@ModDsl
class ElementBuilder(override val t: Element = Element()) : Builder<Element> {

    var id by t::id
    var label by t::label
    var description by t::description
    var comments by t::comments
    var icon by t::icon
    var isAspect by t::isAspect
    var isHidden by t::isHidden
    var noartneeded by t::noartneeded

    val aspects by mprop(t.aspects, t.aspects_add, t.aspects_remove)

    val slots by lprop(t.slots, t.slots_append, t.slots_prepend, t.slots_remove)

    val xtriggers by mprop(t.xtriggers, t.xtriggers_add, t.xtriggers_remove)

    val induces by lprop(t.induces, t.induces_append, t.induces_prepend, t.induces_remove)

    var decayTo by t::decayTo
    var lifetime by t::lifetime
    var resaturate by t::resaturate
    var unique by t::unique
    var uniquenessgroup by t::uniquenessgroup
    var inherits by t::inherits
    var verbicon by t::verbicon
    var lever by t::lever
    var extends by t::extends
    var deleted by t::deleted
    
}