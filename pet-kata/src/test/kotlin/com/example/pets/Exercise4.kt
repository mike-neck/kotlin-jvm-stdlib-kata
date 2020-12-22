package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.util.stream.*
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise4 {

  @TestFactory
  fun ageStatisticsOfPets(people: People) =
      dynamic {
        failAll("Refactor to kotlin stdlib. Do not forget to remove this line.")
        val petAges =
            people.stream()
                .map { it.pets }
                .flatMap { it.stream() }
                .mapToInt { it.age }
                .boxed()
                .collect(Collectors.toList())

        val uniqueAges = petAges.toSet()
        test("ages are 1,2,3,4") { uniqueAges shouldBe setOf(1, 2, 3, 4) }

        val statistics = petAges.stream().mapToInt { it }.summaryStatistics()
        test("min") { statistics.min shouldBe petAges.stream().mapToInt { it }.min().orElse(0) }
        test("max") { statistics.max shouldBe petAges.stream().mapToInt { it }.max().orElse(0) }
        test("sum") { statistics.sum shouldBe petAges.stream().mapToInt { it }.sum() }
        test("average") {
          statistics.average should
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
        failAll("Refactor the stream API code to kotlin stdlib. Do not forget to remove this line.")
        val person: Person =
            people.stream().filter { it.named("Bob Smith") }.findFirst().orElseThrow()

        val names: String = person.pets.stream().map { it.name }.collect(Collectors.joining(" & "))

        test("names should be Dolly & Spot") { names shouldBe "Dolly & Spot" }
      }

  @TestFactory
  fun streamToKotlinStdLibRefactor2(people: People) =
      dynamic {
        failAll("Refactor the stream API code to kotlin stdlib. Do not forget to remove this line.")
        val countByPetType: Map<PetType, Long> =
            people.stream()
                .flatMap { it.pets.stream() }
                .collect(Collectors.groupingBy(Pet::type, Collectors.counting()))

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
        failAll("Refactor the stream API code to kotlin stdlib. Do not forget to remove this line.")
        val top3Favorites =
            people.stream()
                .flatMap { it.pets.stream() }
                .collect(Collectors.groupingBy(Pet::type, Collectors.counting()))
                .entries
                .stream()
                .sorted(Comparator.comparingLong { -it.value })
                .limit(3)
                .map { it.toPair() }
                .collect(Collectors.toUnmodifiableList())

        test("it has size 3") { top3Favorites shouldHaveSize 3 }

        test("people with cat is 2") { top3Favorites shouldContain (PetType.CAT to 2L) }
        test("people with dog is 2") { top3Favorites shouldContain (PetType.DOG to 2L) }
        test("people with hamster is 2") { top3Favorites shouldContain (PetType.HAMSTER to 2L) }
      }
}
