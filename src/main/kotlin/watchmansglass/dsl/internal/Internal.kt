package watchmansglass.dsl.internal

import watchmansglass.data.raw.Synopsis
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.primaryConstructor
typealias Init<T> = T.() -> Unit

@DslMarker
annotation class ModDsl

interface Builder<T> {
    val t: T
}

@ModDsl
open class ListBuilder<T>(override val t: MutableList<T>) : MutableList<T> by t, Builder<MutableList<T>> {

    operator fun T.unaryPlus(): T {
        add(this)
        return this
    }

}

@ModDsl
open class MapBuilder<K, V>(override val t: MutableMap<K, V>) : MutableMap<K, V> by t, Builder<MutableMap<K, V>> {

    infix fun K.of(v: V) {
        put(this, v)
    }

    operator fun K.unaryMinus() {
        remove(this)
    }

    operator fun Sequence<Pair<K, V>>.unaryPlus() = forEach(t::put)
}

@ModDsl
class MapProperty<K, V>(
    m: MutableMap<K, V>,
    private val add: MutableMap<K, V>,
    private val remove: MutableList<K>,
) : MapBuilder<K, V>(m) {

    fun add(init: Init<MapBuilder<K, V>>) = MapBuilder(add).init()

    fun remove(init: Init<ListBuilder<K>>) = ListBuilder(remove).init()

}

@ModDsl
class ListProperty<T>(
    l: MutableList<T>,
    private val append: MutableList<T>,
    private val prepend: MutableList<T>,
    private val remove: MutableList<String>,
) : ListBuilder<T>(l) {

    fun append(init: Init<ListBuilder<T>>) = ListBuilder(append).init()
    fun prepend(init: Init<ListBuilder<T>>) = ListBuilder(prepend).init()
    fun remove(init: Init<ListBuilder<String>>) = ListBuilder(remove).init()
}

typealias MProp<T> = Init<MapProperty<String, T>>
typealias LProp<T> = Init<ListProperty<T>>

class MapPropertyDelegate<K, V>(
    private val m: MutableMap<K, V>,
    private val add: MutableMap<K, V>,
    private val remove: MutableList<K>,
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): (Init<MapProperty<K, V>>) -> Unit = { init ->
        MapProperty(m, add, remove).init()
    }

}

class ListPropertyDelegate<T>(
    private val l: MutableList<T>,
    private val append: MutableList<T>,
    private val prepend: MutableList<T>,
    private val remove: MutableList<String>,
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): (Init<ListProperty<T>>) -> Unit = { init ->
        ListProperty(l, append, prepend, remove).init()
    }

}

class BuilderDelegate<T, B : Builder<T>>(
    private val prop: KMutableProperty<T?>,
    private val start: () -> B,
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): (Init<B>) -> T = { init ->
        val t = start().apply(init).t
        prop.setter.call(t)
        t
    }

}

class BuilderDelegateNN<T, B : Builder<T>>(
    private val prop: KMutableProperty<T>,
    private val start: () -> B,
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): (Init<B>) -> T = { init ->
        val t = start().apply(init).t
        prop.setter.call(t)
        t
    }

}

fun <K, V> mprop(m: MutableMap<K, V>, add: MutableMap<K, V>, remove: MutableList<K>) =
    MapPropertyDelegate(m, add, remove)

fun <T> lprop(l: MutableList<T>, append: MutableList<T>, prepend: MutableList<T>, remove: MutableList<String>) =
    ListPropertyDelegate(l, append, prepend, remove)

inline fun <reified T : Any, B : Builder<T>> builder(prop: KMutableProperty<T?>, noinline start: (T) -> B) =
    BuilderDelegate(prop) { start(prop.getter.call() ?: T::class.primaryConstructor!!.callBy(emptyMap())) }

inline fun <reified T : Any, B : Builder<T>> builder(prop: KMutableProperty<T>, noinline start: (T) -> B) =
    BuilderDelegateNN(prop) { start(prop.getter.call()) }