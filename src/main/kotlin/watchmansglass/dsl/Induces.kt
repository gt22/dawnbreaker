package watchmansglass.dsl

import watchmansglass.data.raw.Expulsion
import watchmansglass.data.raw.Induces
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.Init
import watchmansglass.dsl.internal.ModDsl
import watchmansglass.dsl.internal.builder

@ModDsl
class InducesBuilder(override val t: Induces = Induces()) : Builder<Induces> {

    var id by t::id
    var chance by t::chance
    var additional by t::additional
    val expulsion by builder(t::expulsion, ::ExpulsionBuilder)
}