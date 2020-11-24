package watchmansglass.mods.compat.decks

import watchmansglass.dsl.ModBuilder
import watchmansglass.vanilla


fun getDeinternalizedName(recipeId: String) = "deck_${recipeId}_internal"

fun ModBuilder.deinternalizeDecks() = source("deinternalized/decks") {
    vanilla.sources.flatMap { it.value.recipes }.filter
    {

    }
}