package watchmansglass.mods.seeker

import watchmansglass.data.raw.Element
import watchmansglass.dsl.ModBuilder
import watchmansglass.vanilla

lateinit var coldtrail: Element
lateinit var trace: Element
lateinit var curseGeneric: Element
lateinit var curseDefeated: Element

lateinit var value: Element
lateinit var price: Element
lateinit var coinvalue: Element
lateinit var coinprice: Element

fun ModBuilder.stats() = source("stats") {

    elements {
        coldtrail = element("coldtrail") {
            label = "A cold trail"
            description = "Whoever keeps the relic, does not want it to be found " +
                    "[Seven of these would mean that relic is lost for me]"
            icon = vanilla["seasonsilence"]
        }
        trace = element("trace") {
            label = "Awareness"
            description = "They know I look for them. Do they know where I look?"
            icon = vanilla["rumour"]
        }
        curseGeneric = element("curse.generic") {
            label = "A lingering curse"
            description = "Relic has its protections. As within, so without. Even their traces leave me vulnerable." +
                    " [Seven of these might drive me insane]"
            icon = vanilla["curse_visions"]
            lifetime = 600
            aspects {
                vanilla["influence"] of 1
                seeker["lantern"] of 4
                seeker["winter"] of 4
            }
        }
        curseDefeated = element("curse.defeated") {
            label = "A subverted curse"
            description = "It still has power, but it’s not dangerous to me"
            icon = vanilla["curse_dread"]
            lifetime = curseGeneric.lifetime
            aspects {
                putAll(curseGeneric.aspects)
            }
        }
        element("coin1") {
            label = "Iron coin"
            description = "My employer gave me strange money in preparation for this job. This one looks common. " +
                    "It has violent scenes on reverse side."
            icon = vanilla["compensation"]
            aspects {
                seeker["coinvalue"] of 1
            }
        }
        element("coin10") {
            label = "Silver coin"
            description = "My employer gave me strange money in preparation for this job. This one looks rare. " +
                    "It has... interesting scenes on reverse side."
            icon = vanilla["compensationc"]
            aspects {
                seeker["coinvalue"] of 10
            }
        }
    }

    aspects {
        value = aspect("value") {
            label = "Value"
            description = "Some things worth more than others"
            icon = vanilla["compensationvalue"]
        }
        price = aspect("price") {
            label = "Price"
            description = "'PRICE, n. Value, plus a reasonable sum for the wear and tear of conscience in demanding it.'" +
                    " - Bierce."
        }
        coinvalue = aspect("coinvalue") {
            label = "Coin value"
            description = "Some things are too precious to buy with regular money. This may help."
            icon = vanilla["compensationvalue"]
        }
        coinprice = aspect("coinprice") {
            label = "Coin price"
            description = "Some things are too precious to buy with regular money."
            icon = vanilla["price"]
        }
    }

}