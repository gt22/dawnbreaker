package dawnbreaker.dsl

import dawnbreaker.data.raw.Portal
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.lprop

@ModDsl
class PortalBuilder(override var t: Portal = Portal()) : Builder<Portal> {

    var id by t::id
    var icon by t::icon
    var label by t::label
    var description by t::description
    var otherworldId by t::otherworldId
    var egressId by t::egressId
    val consequences by lprop(t.consequences, t.consequences_append, t.consequences_prepend, t.consequences_remove)

}