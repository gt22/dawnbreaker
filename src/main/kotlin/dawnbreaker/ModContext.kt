package dawnbreaker

import dawnbreaker.data.raw.Data
import dawnbreaker.data.raw.Mod

class ModContext {

    val mods = mutableListOf<Mod>()

    fun add(mod: Mod) = mods.add(mod)

    inline fun <reified T : Data> find(id: String): String {
        for(m in mods) {
            if(m.exists<T>(id)) {
                return m.find<T>(id, false)
            }
        }
        throw IllegalArgumentException("${T::class.simpleName} '$id' was not found in context")
    }

}