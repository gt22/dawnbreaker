package watchmansglass.mods.compat

import watchmansglass.dsl.mod
import watchmansglass.mods.compat.dependecies.dependencies
import watchmansglass.mods.compat.subversion.subversion
import java.nio.file.Paths

val fancyname = "Frgm's Compatibility Mod"

fun compat() = mod {

    synopsis {
        name = "frgm.compat"
        author = "Frgm"
        version = "0.1.0"
        description = "Mod compatibility tool"
    }
    images = Paths.get("compat.images")
    additionalfiles = Paths.get("compat.additional")
    subversion()
    dependencies()

}