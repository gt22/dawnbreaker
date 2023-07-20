package dawnbreaker.dsl

import dawnbreaker.data.raw.Achievement
import dawnbreaker.dsl.internal.Builder
import dawnbreaker.dsl.internal.ModDsl

@ModDsl
class AchievementBuilder(override val t: Achievement = Achievement()) : Builder<Achievement> {

    var id by t::id
    var isCategory by t::isCategory
    var label by t::label
    var icon by t::iconUnlocked
    var category by t::category
    var singleDescription by t::singleDescription
    var description by t::descriptionUnlocked
    var validateOnStorefront by t::validateOnStorefront

}