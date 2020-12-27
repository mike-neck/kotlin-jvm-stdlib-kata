package data

typealias Bag<T> = Map<T, Int>

operator fun <T : Any> Bag<T>.plus(t: T): Bag<T> =
    mutableMapOf<T, Int>().also { it.putAll(this) }.also { it.add(t) }

typealias MutableBag<T> = MutableMap<T, Int>

fun <T : Any> MutableBag<T>.add(t: T): Unit =
    this.compute(t) { _, count -> if (count == null) 1 else count + 1 }.let {}

operator fun <T : Any> MutableBag<T>.plusAssign(t: T): Unit =
    this.compute(t) { _, count -> if (count == null) 1 else count + 1 }.let {}

typealias Multimap<K, V> = Map<K, List<V>>

fun <K : Any, V : Any> Multimap<K, V>.getOrEmpty(key: K): List<V> = this[key] ?: emptyList()

typealias MutableMultimap<K, V> = MutableMap<K, MutableList<V>>

typealias SetMultimap<K, V> = Map<K, Set<V>>

typealias MutableSetMultimap<K, V> = MutableMap<K, MutableSet<V>>

typealias PartitionList<T> = Pair<List<T>, List<T>>

fun <T> emptyPartitionList(): PartitionList<T> = emptyList<T>() to emptyList()

val <T : Any> PartitionList<T>.selected: List<T>
  get() = this.first

val <T : Any> PartitionList<T>.rejected: List<T>
  get() = this.second
