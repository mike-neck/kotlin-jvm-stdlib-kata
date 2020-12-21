package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
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
        test("The count of snake is 2") { petTypeCounts[PetType.SNAKE] shouldBe 1 }
        test("The count of turtle is 2") { petTypeCounts[PetType.TURTLE] shouldBe 1 }
        test("The count of bird is 2") { petTypeCounts[PetType.BIRD] shouldBe 1 }
      }
}
