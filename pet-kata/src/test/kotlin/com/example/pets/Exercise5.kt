package com.example.pets

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
}
