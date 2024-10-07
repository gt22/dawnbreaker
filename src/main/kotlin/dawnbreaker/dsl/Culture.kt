package dawnbreaker.dsl

import dawnbreaker.data.raw.primary.Culture
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl
import dawnbreaker.dsl.internal.mprop

@ModDsl
class CultureBuilder(override val t: Culture = Culture()) : Builder<Culture> {

    var id by t::id
    var endonym by t::endonym
    var exonym by t::exonym
    var fontscript by t::fontscript
    var boldallowed by t::boldallowed

    val uilabels by mprop(t.uilabels, t.uilabels_add, t.uilabels_remove)

}