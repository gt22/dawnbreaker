package watchmansglass.dsl

import watchmansglass.ModContext
import watchmansglass.data.raw.*
import watchmansglass.dsl.internal.*


@ModDsl
class SourceBuilder(val t: Source = Source()) {

    private inline fun <reified T : Data, B : Builder<T>> build(
        bb: ListBuilder<T>,
        id: String,
        init: Init<B>,
        b: (T) -> B,
        def: (String) -> T
    ): T {
        val fid = this@SourceBuilder.t.prefix(id)
        val base = this@SourceBuilder.t.lookup<T>(fid)
        return if (base == null) {
            val e = b(def(fid)).apply(init).t
            bb.add(e)
            e
        } else {
            b(base).apply(init).t
        }
    }

    @ModDsl
    inner class ElementsBuilder(l: MutableList<Element>) : ListBuilder<Element>(l) {
        fun element(id: String = "", init: Init<ElementBuilder>) =
            this@SourceBuilder.build(this, id, init, ::ElementBuilder, ::Element)
    }

    @ModDsl
    inner class AspectsBuilder(l: MutableList<Element>) : ListBuilder<Element>(l) {
        fun aspect(id: String = "", init: Init<ElementBuilder>) =
            this@SourceBuilder.build(this, id, init, ::ElementBuilder) { Element(id, isAspect = true) }
    }

    @ModDsl
    inner class RecipesBuilder(l: MutableList<Recipe>) : ListBuilder<Recipe>(l) {
        fun recipe(id: String = "", init: Init<RecipeBuilder>) =
            this@SourceBuilder.build(this, id, init, ::RecipeBuilder, ::Recipe)
    }

    @ModDsl
    inner class DecksBuilder(l: MutableList<Deck>) : ListBuilder<Deck>(l) {
        fun deck(id: String = "", init: Init<DeckBuilder>) =
            this@SourceBuilder.build(this, id, init, ::DeckBuilder, ::Deck)
    }

    @ModDsl
    inner class LegaciesBuilder(l: MutableList<Legacy>) : ListBuilder<Legacy>(l) {
        fun legacy(id: String = "", init: Init<LegacyBuilder>) =
            this@SourceBuilder.build(this, id, init, ::LegacyBuilder, ::Legacy)
    }

    @ModDsl
    inner class EndingsBuilder(l: MutableList<Ending>) : ListBuilder<Ending>(l) {
        fun ending(id: String = "", init: Init<EndingBuilder>) =
            this@SourceBuilder.build(this, id, init, ::EndingBuilder, ::Ending)
    }

    @ModDsl
    inner class VerbsBuilder(l: MutableList<Verb>) : ListBuilder<Verb>(l) {
        fun verb(id: String = "", init: Init<VerbBuilder>) =
            this@SourceBuilder.build(this, id, init, ::VerbBuilder, ::Verb)
    }

    @ModDsl
    inner class CulturesBuilder(l: MutableList<Culture>) : ListBuilder<Culture>(l) {
        fun culture(id: String = "", init: Init<CultureBuilder>) =
            this@SourceBuilder.build(this, id, init, ::CultureBuilder, ::Culture)
    }

    fun elements(init: Init<ElementsBuilder>) = ElementsBuilder(t.elements).apply(init)
    fun aspects(init: Init<AspectsBuilder>) = AspectsBuilder(t.elements).apply(init)
    fun recipes(init: Init<RecipesBuilder>) = RecipesBuilder(t.recipes).apply(init)
    fun decks(init: Init<DecksBuilder>) = DecksBuilder(t.decks).apply(init)
    fun legacies(init: Init<LegaciesBuilder>) = LegaciesBuilder(t.legacies).apply(init)
    fun endings(init: Init<EndingsBuilder>) = EndingsBuilder(t.endings).apply(init)
    fun verbs(init: Init<VerbsBuilder>) = VerbsBuilder(t.verbs).apply(init)
    fun cultures(init: Init<CulturesBuilder>) = CulturesBuilder(t.cultures).apply(init)
}


@ModDsl
class ModBuilder(override val t: Mod = Mod()) : Builder<Mod> {

    val prefix by t::prefix_v

    fun addToContext(c: ModContext) = c.add(t)

    val synopsis by builder(t::synopsis, ::SynopsisBuilder)
    var images by t::images
    var additionalfiles by t::additionalfiles

    fun source(id: String, init: Init<SourceBuilder>) {
        t.sources[id.removeSuffix(".json") + ".json"] = SourceBuilder(Source(t, id)).apply(init).t
    }

    fun find(id: String, check: Boolean = false) = t.find<Data>(id, check)


}

fun mod(prefix: String = "", init: Init<ModBuilder>) = ModBuilder(Mod(prefix)).apply(init).t