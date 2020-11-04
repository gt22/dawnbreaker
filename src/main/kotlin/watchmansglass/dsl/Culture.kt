package watchmansglass.dsl

import watchmansglass.data.raw.Culture
import watchmansglass.dsl.internal.Builder
import watchmansglass.dsl.internal.ModDsl
import watchmansglass.dsl.internal.mprop

@ModDsl
class CultureBuilder(override val t: Culture = Culture()) : Builder<Culture> {

    var id by t::id
    var endonym by t::endonym
    var exonym by t::exonym
    var fontscript by t::fontscript
    var boldallowed by t::boldallowed

    val uilabels by mprop(t.uilabels, t.uilabels_add, t.uilabels_remove)

}