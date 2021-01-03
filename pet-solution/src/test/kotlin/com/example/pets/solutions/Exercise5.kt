package com.example.pets.solutions

import com.example.pets.People
import com.example.pets.Person
import com.example.pets.Pet
import com.example.pets.PetType
import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.PartitionList
import data.rejected
import data.selected
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise5 {

  @TestFactory
  fun partitionPetAndNonPetPeople(people: People) =
      dynamic {
        val partitionHavingPetAndNotHaving: PartitionList<Person> =
            people.partition { it.pets.isNotEmpty() }

        test("people with pets are 7") { partitionHavingPetAndNotHaving.selected shouldHaveSize 7 }
        test("people without pets are 1") {
          partitionHavingPetAndNotHaving.rejected shouldHaveSize 1
        }
      }

  @TestFactory
  fun oldestPet(people: People) =
      dynamic {
        val oldest: Pet =
            people.flatMap { it.pets }.maxByOrNull { it.age } ?: throw NoSuchElementException()

        test("type is dog") { oldest.type shouldBe PetType.DOG }
        test("age is 4") { oldest.age shouldBeExactly 4 }
      }

  @Test
  fun averageOfPetAge(people: People) {
    val averageAge: Double = people.flatMap { it.pets.map { pet -> pet.age } }.average()

    averageAge should 1.8888888888888888.withDelta(0.00001)
  }

  @Test
  fun addPetAgesToExistingSet(people: People) {
    val petAges: MutableSet<Int> = mutableSetOf(5)
    people.flatMap { it.pets }.mapTo(petAges) { it.age }

    val expected = setOf(1, 2, 3, 4, 5)

    petAges shouldBe expected
  }

  @Test
  fun makeBuilder(expected: People) {

    val peopleByDsl: People =
        people {
          person {
            firstName = "Mary"
            lastName = "Smith"
            pet {
              type = PetType.CAT
              name = "Tabby"
              age = 2
            }
          }
          person {
            firstName = "Bob"
            lastName = "Smith"
            pet {
              type = PetType.CAT
              name = "Dolly"
              age = 3
            }
            pet {
              type = PetType.DOG
              name = "Spot"
              age = 2
            }
          }
          person {
            firstName = "Ted"
            lastName = "Smith"
            pet {
              type = PetType.DOG
              name = "Spike"
              age = 4
            }
          }
          person {
            firstName = "Jake"
            lastName = "Snake"
            pet {
              type = PetType.SNAKE
              name = "Serpy"
              age = 1
            }
          }
          person {
            firstName = "Barry"
            lastName = "Bird"
            pet {
              type = PetType.BIRD
              name = "Tweety"
              age = 2
            }
          }
          person {
            firstName = "Terry"
            lastName = "Turtle"
            pet {
              type = PetType.TURTLE
              name = "Speedy"
              age = 1
            }
          }
          person {
            firstName = "Harry"
            lastName = "Hamster"
            pet {
              type = PetType.HAMSTER
              name = "Fuzzy"
              age = 1
            }
            pet {
              type = PetType.HAMSTER
              name = "Wuzzy"
              age = 1
            }
          }
          person {
            firstName = "John"
            lastName = "Doe"
          }
        }

    peopleByDsl shouldBe expected
  }
}

private fun people(configure: People.() -> Unit): People = People(mutableListOf()).also(configure)

private fun People.person(configure: PersonBuilder.() -> Unit) {
  val personBuilder = PersonBuilder()
  personBuilder.configure()
  this.items.add(PersonBuilder().apply(configure).toPerson())
}

data class PersonBuilder(
    var firstName: String = "",
    var lastName: String = "",
    var pets: MutableList<Pet> = mutableListOf()
) {
  fun pet(configure: PetBuilder.() -> Unit) {
    val pet = PetBuilder()
    pet.configure()
    pets.add(pet.toPet())
  }
  fun toPerson(): Person = Person(firstName, lastName, pets)
}

data class PetBuilder(
    var type: PetType = PetType.UNKNOWN, var name: String = "", var age: Int = 0
) {
  fun toPet(): Pet = Pet(type, name, age)
}
