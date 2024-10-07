package dawnbreaker.data.raw.secondary

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

@Serializable
data class Mutation(
    var filter: String = "",
    var mutate: String = "",
    var level: String = "",
    var additive: Boolean = false
) {

    companion object {

        fun parseList(data: JsonElement): JsonArray = when (data) {
            is JsonObject,
            is JsonPrimitive -> JsonArray(listOf(parse(data)))

            is JsonArray -> JsonArray(data.map { parse(it) }.toMutableList())
            else -> error("Unable to parse mutation list from '$data'")
        }

        private fun parse(data: JsonElement): JsonObject = when (data) {
            is JsonPrimitive -> JsonObject(mapOf("id" to data))
            is JsonObject -> data
            else -> error("Unable to parse mutation from $data")
        }

    }
}

object MutationListSerializer : JsonTransformingSerializer<MutableList<Mutation>>(serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return Mutation.parseList(element)
    }
}
