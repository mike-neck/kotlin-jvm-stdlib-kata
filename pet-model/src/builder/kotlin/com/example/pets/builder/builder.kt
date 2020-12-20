package com.example.pets.builder

import com.example.pets.Person
import com.example.pets.Pet
import com.example.pets.PetType

fun <T: Any> iterables(configure: IterableBuilder<T>.() -> Unit): IterableBuilder<T> =
  IterableBuilder(mutableListOf<T>()).also(configure)

class Mutator<T: Any>(internal val item: IterableBuilder<T>)

class IterableBuilder<T: Any>(internal val mutableList: MutableList<T>) {

  val add: Mutator<T> = Mutator(this)

  fun toIterable(): Iterable<T> = toList()
  fun toMutableList(): MutableList<T> = mutableList
  fun toList(): List<T> = ArrayList(mutableList).toList()
}

fun Mutator<Person>.person(configure: PersonBuilder.() -> Unit): Unit =
  this.item.mutableList.add(PersonBuilder().also(configure).toPerson()).let {  }

class PersonBuilder(
  var firstName: String?,
  var lastName: String?,
  private val owns: MutableList<Pet>
) {

  constructor(): this(null, null, mutableListOf())

  fun pet(configure: PetBuilder.() -> Unit) {
    val petBuilder = PetBuilder(null, null, null)
    petBuilder.configure()
    owns.add(petBuilder.toPet())
  }

  fun toPerson(): Person = Person(
    firstName ?: throw invalidData(firstName, lastName),
    lastName ?: throw invalidData(firstName, lastName),
    owns
  )

  companion object {
    fun invalidData(firstName: String?, lastName: String?): Throwable =
      IllegalArgumentException("all parameters are required[firstName:$firstName,lastName:$lastName]")
  }
}

class PetBuilder(
  var type: PetType?,
  var name: String?,
  var age: Int?
) {
  fun toPet(): Pet = Pet(
    type ?: throw invalidData(type, name, age),
    name ?: throw invalidData(type, name, age),
    age ?: throw invalidData(type, name, age)
  )

  companion object {
    private fun invalidData(type: PetType?, name: String?, age: Int?): Throwable
        = IllegalArgumentException("all parameters are required[type:$type,name:$name,age:$age]")
  }
}
