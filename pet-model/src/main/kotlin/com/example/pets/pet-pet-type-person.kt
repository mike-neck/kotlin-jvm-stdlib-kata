package com.example.pets

import data.Bag
import org.jetbrains.annotations.TestOnly

operator fun <T: Any, R> Iterable<T>.invoke(imitation: () -> R): R = imitation()

enum class PetType {
  CAT,
  DOG,
  HAMSTER,
  TURTLE,
  BIRD,
  SNAKE,
  ;
}

data class Pet(
  val type: PetType,
  val name: String,
  val age: Int
)

data class Person(
  val firstName: String,
  val lastName: String,
  private val ownsPets: MutableList<Pet>
) {

  constructor(): this("", "", mutableListOf())

  @TestOnly
  operator fun <T> invoke(imitation: () -> T): T = imitation()

  val pets: List<Pet> get() = listOf(*ownsPets.toTypedArray())

  val petTypes: Bag<PetType> get() = pets.groupBy { it.type }.mapValues { it.value.size }

  val numberOfPets: Int get() = pets.size

  val isPetPerson: Boolean get() = pets.isNotEmpty()

  fun named(name: String): Boolean = name == "$firstName $lastName"

  fun hasPet(type: PetType): Boolean = pets.any { it.type == type }

  fun addPet(type: PetType, name: String, age: Int) = addPet(Pet(type, name, age))

  fun addPet(pet: Pet): Unit = ownsPets.add(pet).let { }
}
