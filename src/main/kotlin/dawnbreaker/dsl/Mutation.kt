package dawnbreaker.dsl

import dawnbreaker.data.raw.secondary.Mutation
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl

@ModDsl
class MutationBuilder(override val t: Mutation = Mutation()) : Builder<Mutation> {
    
    var filter by t::filter
    var mutate by t::mutate
    var level by t::level
    var additive by t::additive
    
}