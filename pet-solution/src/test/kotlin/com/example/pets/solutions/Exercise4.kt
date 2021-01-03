package com.example.pets.solutions

import com.example.pets.People
import com.example.pets.Person
import com.example.pets.PetType
import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import io.kotest.matchers.sequences.shouldContain
import io.kotest.matchers.sequences.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise4 {

  @TestFactory
  fun ageStatisticsOfPets(people: People) =
      dynamic {
        val petAges = people.flatMap { person -> person.pets.map { pet -> pet.age } }

        val uniqueAges = petAges.toSet()
        test("ages are 1,2,3,4") { uniqueAges shouldBe setOf(1, 2, 3, 4) }

        test("min") {
          petAges.minOrNull() shouldBe petAges.stream().mapToInt { it }.min().orElse(0)
        }
        test("max") {
          petAges.maxOrNull() shouldBe petAges.stream().mapToInt { it }.max().orElse(0)
        }
        test("sum") { petAges.sum() shouldBe petAges.stream().mapToInt { it }.sum() }
        test("average") {
          petAges.average() should
              petAges.stream().mapToInt { it }.average().orElse(0.0).withDelta(0.0)
        }

        test("all ages should be greater than 0") {
          petAges.stream().allMatch { it > 0 } shouldBe true
        }
        test("no any ages do not equal to 0") {
          petAges.stream().anyMatch { it == 0 } shouldBe false
        }
        test("no any ages do not less than 0") {
          petAges.stream().noneMatch { it < 0 } shouldBe true
        }
      }

  @TestFactory
  fun streamsToKotlinStdLibRefactor1(people: People) =
      dynamic {
        val person: Person = people.first { it.named("Bob Smith") }

        val names: String = person.pets.joinToString(" & ") { it.name }

        test("names should be Dolly & Spot") { names shouldBe "Dolly & Spot" }
      }

  @TestFactory
  fun streamToKotlinStdLibRefactor2(people: People) =
      dynamic {
        val countByPetType: Map<PetType, Long> =
            people.flatMap { it.pets }.groupingBy { it.type }.eachCount().mapValues {
              it.value.toLong()
            }

        test("cat -> 2") { countByPetType.getValue(PetType.CAT) shouldBe 2 }
        test("dog -> 2") { countByPetType.getValue(PetType.DOG) shouldBe 2 }
        test("hamster -> 2") { countByPetType.getValue(PetType.HAMSTER) shouldBe 2 }
        test("snake -> 1") { countByPetType.getValue(PetType.SNAKE) shouldBe 1 }
        test("turtle -> 1") { countByPetType.getValue(PetType.TURTLE) shouldBe 1 }
        test("bird -> 1") { countByPetType.getValue(PetType.BIRD) shouldBe 1 }
      }

  @TestFactory
  fun streamToKotlinStdLibRefactor3(people: People) =
      dynamic {
        val top3Favorites =
            people.flatMap { it.pets }
                .groupingBy { it.type }
                .eachCount()
                .asSequence()
                .sortedByDescending { it.value }
                .take(3)
                .map { it.key to it.value.toLong() }

        test("it has size 3") { top3Favorites shouldHaveSize 3 }

        test("people with cat is 2") { top3Favorites shouldContain (PetType.CAT to 2L) }
        test("people with dog is 2") { top3Favorites shouldContain (PetType.DOG to 2L) }
        test("people with hamster is 2") { top3Favorites shouldContain (PetType.HAMSTER to 2L) }
      }
}
