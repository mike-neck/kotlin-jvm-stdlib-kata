package com.example.pets

import com.example.pets.builder.person
import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.PartitionList
import data.emptyPartitionList
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
        val partitionHavingPetAndNotHaving: PartitionList<Person> = people { emptyPartitionList() }

        test("people with pets are 7") { partitionHavingPetAndNotHaving.selected shouldHaveSize 7 }
        test("people without pets are 1") {
          partitionHavingPetAndNotHaving.rejected shouldHaveSize 1
        }
      }

  @TestFactory
  fun oldestPet(people: People) =
      dynamic {
        val oldest: Pet = people { Pet() }

        test("type is dog") { oldest.type shouldBe PetType.DOG }
        test("age is 4") { oldest.age shouldBeExactly 4 }
      }

  @Test
  fun averageOfPetAge(people: People) {
    val averageAge: Double = people { 0.0 }

    averageAge should 1.8888888888888888.withDelta(0.00001)
  }

  @Test
  fun addPetAgesToExistingSet(people: People) {
    val petAges = mutableSetOf(5)
    // Add pet ages to petAges set

    val expected = setOf(1, 2, 3, 4, 5)

    petAges shouldBe expected
  }

  @Test
  fun makeBuilder(expected: People) {
    // TODO Remove comment block
    // TODO Make the DSL code to be compiled.
    // TODO Make the DSL code pass its assertion.
    // NOTE: Originally this test is refactoring Java Collection to Eclipse collection
    //    But you may have some knowledge on Kotlin collection,
    //    this test has been changed to the one making builder DSL in Kotlin.

//    val peopleByDsl: People = people {
//      person {
//        firstName = "Mary"
//        lastName = "Smith"
//        pet {
//          type = PetType.CAT
//          name = "Tabby"
//          age = 2
//        }
//      }
//      person {
//        firstName = "Bob"
//        lastName = "Smith"
//        pet {
//          type = PetType.CAT
//          name = "Dolly"
//          age = 3
//        }
//        pet {
//          type = PetType.DOG
//          name = "Spot"
//          age = 2
//        }
//      }
//      person {
//        firstName = "Ted"
//        lastName = "Smith"
//        pet {
//          type = PetType.DOG
//          name = "Spike"
//          age = 4
//        }
//      }
//      person {
//        firstName = "Jake"
//        lastName = "Snake"
//        pet {
//          type = PetType.SNAKE
//          name = "Serpy"
//          age = 1
//        }
//      }
//      person {
//        firstName = "Barry"
//        lastName = "Bird"
//        pet {
//          type = PetType.BIRD
//          name = "Tweety"
//          age = 2
//        }
//      }
//      person {
//        firstName = "Terry"
//        lastName = "Turtle"
//        pet {
//          type = PetType.TURTLE
//          name = "Speedy"
//          age = 1
//        }
//      }
//      person {
//        firstName = "Harry"
//        lastName = "Hamster"
//        pet {
//          type = PetType.HAMSTER
//          name = "Fuzzy"
//          age = 1
//        }
//        pet {
//          type = PetType.HAMSTER
//          name = "Wuzzy"
//          age = 1
//        }
//      }
//      person {
//        firstName = "John"
//        lastName = "Doe"
//      }
//    }
//
//    peopleByDsl shouldBe expected
  }
}
