package watchmansglass.mods.seeker

import watchmansglass.dsl.ModBuilder
import watchmansglass.dsl.SourceBuilder
import watchmansglass.vanilla

val PRINCIPLES by lazy {
    listOf("lantern", "forge", "edge", "winter", "heart", "grail", "moth", "knock").map(seeker::get)
}

fun ModBuilder.aspects() = source("aspects") {
    principles()
    types()
}

fun SourceBuilder.principles() = aspects {
    aspect("lantern") {
        label = "Lantern"
        icon = vanilla["lantern"]
        description = "'Life is a pure flame, and we live by an invisible Sun within us.' - Thomas Browne. " +
                "[Lantern is the principle of the secret place sometimes called the House of the Sun," +
                " and of the light above it.]"
    }
    aspect("forge") {
        label = "Forge"
        icon = vanilla["forge"]
        description = "'Fire', I once read, 'is the winter that warms and the spring that consumes.'" +
                " [The principle of the Forge transforms and destroys.]"
    }
    aspect("edge") {
        label = "Edge"
        icon = vanilla["edge"]
        description = "All conquest occurs at the Edge. " +
                "One who dwells there is blind, and cannot be wounded. " +
                "Another is strong, and grows stronger. " +
                "[Edge is the principle of battle and of struggle.]"
    }
    aspect("winter") {
        label = "Winter"
        icon = vanilla["winter"]
        description = "... " +
                "[Winter is the principle of silence, " +
                "of endings, " +
                "and of those things that are not quite dead.]"
    }
    aspect("heart") {
        label = "Heart"
        icon = vanilla["heart"]
        description = "The Heart Relentless beats to protect the skin of the world we understand. " +
                "[The Heart is the principle that continues and preserves.]"
    }
    aspect("grail") {
        label = "Grail"
        icon = vanilla["grail"]
        description = "Hunger, lust, the drowning waters. " +
                "[The principle of the Grail honours both the birth and the feast.]"
    }
    aspect("moth") {
        label = "Moth"
        icon = vanilla["moth"]
        description = "I knew a man who captured moths in a bell-jar. " +
                "On nights like this, he would release them one by one to die in the candle. " +
                "[Moth is the wild and perilous principle of chaos and yearning.]"
    }
    aspect("knock") {
        label = "Knock"
        icon = vanilla["knock"]
        description = "The Knock permits no seal and no isolation." +
                " It thrusts us gleefully out of the safety of ignorance. " +
                "[The Knock is the principle that opens doors and unseams barriers.]"
    }
}

fun SourceBuilder.types() = aspects {
    aspect("local") {
        label = "Local"
        description = "Whatever it is, it won't follow me"
        icon = vanilla["contact.local"]
    }
    aspect("op") {
        label = "Opportunity"
        description = "Unrealised potential."
        icon = vanilla["benign"]
        aspects {
            seeker["local"] of 1
        }
    }
}