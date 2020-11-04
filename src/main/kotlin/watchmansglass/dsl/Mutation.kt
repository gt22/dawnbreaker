package watchmansglass.dsl

import watchmansglass.data.raw.Mutation
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.ModDsl

@ModDsl
class MutationBuilder(override val t: Mutation = Mutation()) : Builder<Mutation> {
    
    var filter by t::filter
    var mutate by t::mutate
    var level by t::level
    var additive by t::additive
    
}