package watchmansglass.data.raw

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

@Serializable
data class XTrigger(
    @Required override var id: String = "",
    var morpheffect: String = "",
    var chance: Int = 0,
    var level: Int = 0
) : Data {

    companion object {

        fun parseList(data: JsonElement): JsonArray = when (data) {
                is JsonObject,
                is JsonPrimitive -> JsonArray(listOf(parse(data)))
                is JsonArray -> JsonArray(data.map { parse(it) }.toMutableList())
                else -> error("Unable to parse XTrigger list from '$data'")
            }

        private fun parse(data: JsonElement): JsonObject = when(data) {
            is JsonPrimitive -> JsonObject(mapOf("id" to data))
            is JsonObject -> data
            else -> error("Unable to parse XTrigger from $data")
        }

    }

}

object XTriggerListSerializer : JsonTransformingSerializer<MutableMap<String, MutableList<XTrigger>>>(serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        check(element is JsonObject)
        return JsonObject(element.mapValues { (_, v) -> XTrigger.parseList(v) })
    }
}

