package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
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

  companion object {
    fun Double.withDelta(delta: Double): Matcher<Double> =
        object : Matcher<Double> {
          override fun test(value: Double): MatcherResult =
              object : MatcherResult {

                override fun failureMessage(): String =
                    "$value does not equal to $this@withDelta within delta $delta"

                override fun negatedFailureMessage(): String =
                    "$value equals to $this@withDelta within delta $delta"

                override fun passed(): Boolean =
                    this@withDelta - delta <= value && value <= this@withDelta + delta
              }
        }
  }
}
