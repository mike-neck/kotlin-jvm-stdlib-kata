package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.Bag
import data.Multimap
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise3 {

  @TestFactory
  fun countByPetType(people: People): Iterable<DynamicTest> =
      dynamic {
        // Frequent pattern
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

  @TestFactory
  fun peopleByLastName(people: People) =
      dynamic {
        // Frequent pattern
        val lastNamesToPeople: MutableMap<String, MutableList<Person>> = mutableMapOf()
        for (person in people) {
          var peopleWithSameName = lastNamesToPeople[person.lastName]
          if (peopleWithSameName == null) {
            peopleWithSameName = mutableListOf()
            lastNamesToPeople[person.lastName] = peopleWithSameName
          }
          peopleWithSameName.add(person)
        }
        test("There is 3 people named Smith") {
          lastNamesToPeople.getValue("Smith") shouldHaveSize 3
        }

        // Hint: use the appropriate method on `people` to create a Multimap<String, Person>
        // Hint: Multimap<K, V> is an alias for `Map<K, List<V>>`
        val groupByLastName: Multimap<String, Person> = people { mapOf() }
        test("There is 3 people named Smith") { groupByLastName.getValue("Smith") shouldHaveSize 3 }
      }
}
