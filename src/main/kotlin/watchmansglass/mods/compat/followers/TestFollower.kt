package watchmansglass.mods.compat.followers

import watchmansglass.dsl.ModBuilder


fun ModBuilder.testFollower() {
    source("followers") {
        elements {
            element("frgm_a") {
                label = "Frgm, an acquaintance"
                aspects {
                    "mortal" of 1
                    "acquaintance" of 1
                    "follower_lustenlightenment" of 1
                }
                description = "TEST"
                xtriggers {
                    "recruiting" of mutableListOf(XTrigge)
                }
            }
        }
    }
}