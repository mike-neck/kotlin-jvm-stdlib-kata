package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.Bag
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise3 {

  @TestFactory
  fun countByPetType(people: People): Iterable<DynamicTest> =
      dynamic {
        val petTypes: List<PetType> = people.flatMap { it.pets }.map { it.type }
        val petTypeCounts: MutableMap<PetType, Int> = mutableMapOf()
        for (petType in petTypes) {
          petTypeCounts.merge(petType, 1) { old, one -> old + one }
        }

        test("The count of cat is 2") { petTypeCounts[PetType.CAT] shouldBe 2 }
        test("The count of dog is 2") { petTypeCounts[PetType.DOG] shouldBe 2 }
        test("The count of hamster is 2") { petTypeCounts[PetType.HAMSTER] shouldBe 2 }
        test("The count of snake is 1") { petTypeCounts[PetType.SNAKE] shouldBe 1 }
        test("The count of turtle is 1") { petTypeCounts[PetType.TURTLE] shouldBe 1 }
        test("The count of bird is 1") { petTypeCounts[PetType.BIRD] shouldBe 1 }

        // Hint: use the appropriate method on `people` to create a Bag<PetType>
        // Hint: Bag<PetType> is alias for Map<PetType, Int> in this tutorial
        val counts: Bag<PetType> = people { mutableMapOf() }

        test("The count of cat is 2") { counts[PetType.CAT] shouldBe 2 }
        test("The count of dog is 2") { counts[PetType.DOG] shouldBe 2 }
        test("The count of hamster is 2") { counts[PetType.HAMSTER] shouldBe 2 }
        test("The count of snake is 1") { counts[PetType.SNAKE] shouldBe 1 }
        test("The count of turtle is 1") { counts[PetType.TURTLE] shouldBe 1 }
        test("The count of bird is 1") { counts[PetType.BIRD] shouldBe 1 }
      }
}
