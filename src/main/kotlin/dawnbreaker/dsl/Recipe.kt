package dawnbreaker.dsl

import dawnbreaker.data.raw.Recipe
import dawnbreaker.data.raw.Slot
import dawnbreaker.dsl.internal.*

@ModDsl
class RecipeBuilder(override val t: Recipe = Recipe()) : Builder<Recipe> {

    var id by t::id
    var label by t::label
    var actionid by t::actionid
    var startdescription by t::startdescription
    var description by t::description
    var warmup by t::warmup
    var craftable by t::craftable
    var hintonly by t::hintonly
    var slots by t::slots

    fun slot(init: Init<SlotBuilder>) {
        if(slots.isEmpty()) slots.add(Slot())
        SlotBuilder(slots[0]).init()
    }

    val effects by mprop(t.effects, t.effects_add, t.aspects_remove)
    val aspects by mprop(t.aspects, t.aspects_add, t.aspects_remove)
    val deckeffects by mprop(t.deckeffects, t.deckeffects_add, t.deckeffects_remove)
    val requirements by mprop(t.requirements, t.requirements_add, t.requirements_remove)
    val extantreqs by mprop(t.extantreqs, t.extantreqs_add, t.extantreqs_remove)
    val tablereqs by mprop(t.tablereqs, t.tablereqs_add, t.tablereqs_remove)
    val fxreqs by mprop(t.fxreqs, t.fxreqs_add, t.fxreqs_remove)
    val internaldeck by builder(t::internaldeck, ::DeckBuilder)

    val linked by lprop(t.linked, t.linked_append, t.linked_prepend, t.linked_remove)
    val alt by lprop(t.alt, t.alt_append, t.alt_prepend, t.alt_remove)
    val mutations by lprop(t.mutations, t.mutations_append, t.mutations_prepend, t.mutations_remove)
    val purge by mprop(t.purge, t.purge_add, t.purge_remove)
    val haltverb by mprop(t.haltverb, t.haltverb_add, t.haltverb_remove)
    val deleteverb by mprop(t.deleteverb, t.deleteverb_add, t.deleteverb_remove)
    var burnimage by t::burnimage
    var portaleffect by t::portaleffect
    var signalimportantloop by t::signalimportantloop
    var signalEndingFlavour by t::signalEndingFlavour
    var ending by t::ending
    var maxexecutions by t::maxexecutions
    var comments by t::comments
    var deleted by t::deleted
    var extends by t::extends
    var inherits by t::inherits
    var audiooneshot by t::audiooneshot

    //for linked & alt only
    var chance by t::chance
    var additional by t::additional
    var expulsion by t::expulsion
    var challenges by t::challenges
    
}