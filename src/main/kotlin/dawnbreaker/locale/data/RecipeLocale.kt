package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Recipe
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class RecipeLocale(
    @Required override var id: String = "",
    var label: String = "",
    var startdescription: String = "",
    var description: String = "",
    var slots: MutableList<SlotLocale> = mutableListOf(),
    val linked: MutableList<RecipeLocale> = mutableListOf(),
    val alt: MutableList<RecipeLocale> = mutableListOf()
) : LocaleData<Recipe> {
    override fun register(base: Recipe, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        slots.zip(base.slots).forEach { (slot, baseSlot) ->
            if(slot.id != baseSlot.id) {
                throw IllegalStateException("Slot id mismatch")
            }
            slot.register(baseSlot, data)
        }
        linked.zip(base.linked).forEach { (recipe, baseRecipe) ->
            if(recipe.id != baseRecipe.id) {
                throw IllegalStateException("Linked recipe id mismatch")
            }
            recipe.register(baseRecipe, data)
        }
        alt.zip(base.alt).forEach { (recipe, baseRecipe) ->
            if(recipe.id != baseRecipe.id) {
                throw IllegalStateException("Alternative recipe id mismatch")
            }
            recipe.register(baseRecipe, data)
        }
    }
}