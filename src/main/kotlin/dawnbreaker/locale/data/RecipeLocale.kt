package dawnbreaker.locale.data

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.primary.Recipe
import dawnbreaker.data.raw.primary.RecipeLinkSerializer
import dawnbreaker.descriptionName
import dawnbreaker.locale.LocaleData
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.serializer

@Serializable
data class RecipeLocale(
    @Required override var id: String = "",
    var preface: String = "",
    var label: String = "",
    var startlabel: String = "",
    var startdescription: String = "",
    @SerialName(descriptionName)
    var description: String = "",
    var preslots: MutableList<SlotLocale> = mutableListOf(),
    var slots: MutableList<SlotLocale> = mutableListOf(),
    @Serializable(RecipeLocaleLinkSerializer::class)
    var linked: MutableList<RecipeLocale> = mutableListOf(),
    @Serializable(RecipeLocaleLinkSerializer::class)
    var alt: MutableList<RecipeLocale> = mutableListOf(),
    var internaldeck: DeckLocale? = null
) : LocaleData<Recipe> {
    override fun register(base: Recipe, data: MutableMap<Data, LocaleData<*>>) {
        super.register(base, data)
        slots.zip(base.slots).forEach { (slot, baseSlot) ->
            if(slot.id != baseSlot.id) {
                throw IllegalStateException("Slot id mismatch at $id: base=${baseSlot.id}, locale=${slot.id}")
            }
            slot.register(baseSlot, data)
        }
        linked.zip(base.linked).forEach { (recipe, baseRecipe) ->
            if(recipe.id != baseRecipe.id) {
                throw IllegalStateException("Linked recipe id mismatch at $id: base=${baseRecipe.id}, locale=${recipe.id}")
            }
            recipe.register(baseRecipe, data)
        }
        alt.zip(base.alt).forEach { (recipe, baseRecipe) ->
            if(recipe.id != baseRecipe.id) {
                throw IllegalStateException("Alternative recipe id mismatch at $id: base=${baseRecipe.id}, locale=${recipe.id}")
            }
            recipe.register(baseRecipe, data)
        }
        if(internaldeck != null) {
            if(base.internaldeck == null) {
                throw IllegalStateException("Internal deck mismatch at $id")
            }
            internaldeck!!.register(base.internaldeck!!, data)
        }
    }
}


object RecipeLocaleLinkSerializer : JsonTransformingSerializer<MutableList<RecipeLocale>>(serializer()) {

    override fun transformDeserialize(element: JsonElement): JsonElement {
        return Recipe.parseLink(element)
    }

}