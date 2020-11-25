package dawnbreaker.dsl

import dawnbreaker.data.raw.Synopsis
import dawnbreaker.dsl.internal.*

@ModDsl
class SynopsisBuilder(override val t: Synopsis = Synopsis()) : Builder<Synopsis> {

    var name by t::name
    var author by t::author
    var version by t::version
    var description by t::description
    var descriptionLong by t::description_long
    val dependencies by builder(t::dependencies, ::ListBuilder)
    
}