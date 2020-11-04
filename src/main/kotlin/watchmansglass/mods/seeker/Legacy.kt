package watchmansglass.mods.seeker

import watchmansglass.dsl.ModBuilder
import watchmansglass.vanilla


fun ModBuilder.legacy() = source("legacy") {

    legacies {
        legacy("seeker") {
            label = "Seeker"
            description = "I make a living searching for things. Today, I was given details on a very profitable job."
            startdescription = "Today, I received instructions on a very profitable job"
            image = "exile"
            newstart = true
            startingverbid = seeker["use"]
            statusbarelements {
                for(i in listOf(coldtrail, curseGeneric, trace, coinvalue)) {
                    +i.id
                }
            }
            effects {
                seeker["seeker_legacy_start"] of 1
            }
        }
    }

    elements {
        element("seeker_legacy_start") {
            label = "A first clue"
            description = "I’ve been hired by a private collector, to ‘retrieve’ a certain relic, " +
                    "and I never ask questions from someone, who pays that generously. " +
                    "They had a clue where it may be."
            icon = vanilla["trainticket"]
            aspects {
                seeker["op"] of 1
            }
        }
    }

}