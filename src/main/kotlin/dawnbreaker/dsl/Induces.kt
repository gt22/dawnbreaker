package dawnbreaker.dsl

import dawnbreaker.data.raw.secondary.Induces
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.builder

@ModDsl
class InducesBuilder(override val t: Induces = Induces()) : Builder<Induces> {

    var id by t::id
    var chance by t::chance
    var additional by t::additional
    val expulsion by builder(t::expulsion, ::ExpulsionBuilder)
}