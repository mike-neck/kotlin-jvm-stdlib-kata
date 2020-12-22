package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.PartitionList
import data.emptyPartitionList
import data.rejected
import data.selected
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
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
}
