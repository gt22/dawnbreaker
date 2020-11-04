package watchmansglass.data.raw

import kotlinx.serialization.Serializable

@Serializable
data class Mutation(
    var filter: String = "",
    var mutate: String = "",
    var level: Int = 0,
    var additive: Boolean = false
)