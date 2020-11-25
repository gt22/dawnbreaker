package dawnbreaker.dsl

import dawnbreaker.data.raw.Ending
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl

@ModDsl
class EndingBuilder(override val t: Ending = Ending()) : Builder<Ending> {

    var id by t::id
    var label by t::label
    var description by t::description
    var image by t::image
    var flavour by t::flavour
    var anim by t::anim
    var achievement by t::achievement
    var comments by t::comments

}