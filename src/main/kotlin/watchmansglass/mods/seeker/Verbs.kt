package watchmansglass.mods.seeker

import watchmansglass.dsl.ModBuilder
import watchmansglass.vanilla


fun ModBuilder.verbs() = source("verbs") {
    verbs {
        verb("use") {
            extends.add(vanilla["use"])
            slot {
                id = "plan"
                label = "Plan"
                required {
                    seeker["op"] of 1
                }
            }
        }
    }
}