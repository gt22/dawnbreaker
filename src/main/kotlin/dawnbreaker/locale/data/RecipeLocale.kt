package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Recipe
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeLocale(
    @Required override var id: String = "",
    var label: String = "",
    var startdescription: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var slots: MutableList<SlotLocale> = mutableListOf(),
    var linked: MutableList<RecipeLocale> = mutableListOf(),
    var alt: MutableList<RecipeLocale> = mutableListOf(),
    var internaldeck: DeckLocale? = null
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
        if(internaldeck != null) {
            if(base.internaldeck == null) {
                throw IllegalStateException("Internal deck mismatch")
            }
            internaldeck!!.register(base.internaldeck!!, data)
        }
    }
}
