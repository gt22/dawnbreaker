package watchmansglass.dsl

import watchmansglass.data.raw.Expulsion
import watchmansglass.dsl.internal.*

@ModDsl
class ExpulsionBuilder(override val t: Expulsion = Expulsion()) : Builder<Expulsion> {
    val filter by builder(t::filter, ::MapBuilder)

    var limit by t::limit

}