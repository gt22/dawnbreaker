package watchmansglass.mods.seeker

import watchmansglass.data.raw.Mod
import watchmansglass.dsl.mod

lateinit var seeker: Mod

fun seekerInit() = mod("seeker.") {
    seeker = t
    synopsis {
        name = "seeker"
        author = "Frgm"
        version = "1.0.0"
        description = "Exile: Knock"
    }
    aspects()
    stats()
    verbs()
    legacy()
}