package dawnbreaker.dsl

import dawnbreaker.data.raw.secondary.Expulsion
import dawnbreaker.dsl.internal.*

@ModDsl
class ExpulsionBuilder(override val t: Expulsion = Expulsion()) : Builder<Expulsion> {
    val filter by builder(t::filter, ::MapBuilder)

    var limit by t::limit

}