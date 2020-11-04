package watchmansglass.mods.compat.subversion

import watchmansglass.dsl.ModBuilder


fun ModBuilder.subversion() {
    source("subversion/aspects") {
        subvertAspects()
    }
    source("subversion/elements") {
        applySubvertAspects()
    }
}