package com.example.pets.solutions

import com.example.pets.People
import com.example.pets.Person
import com.example.pets.PetType
import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.assertAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * In this course, you will work with these functions.
 *
 * - [List.flatMap]
 * - [List.filter]
 * - [List.filterIndexed]
 * - [List.filterNot]
 * - [List.count]
 * - [List.find]
 * - [List.any]
 * - [List.all]
 * - [List.none]
 * - [List.distinct]
 */
@ExtendWith(PetDomain::class)
class Exercise2 {

  @Test
  fun doAnyPeopleHaveCats(people: People) {
    val anySatisfy: Boolean = people.any { it.hasPet(PetType.CAT) }

    anySatisfy shouldBe true
  }

  @Test
  fun doAllPeopleHavePets(people: People) {
    val allPeopleHavePets: Boolean = people.all { it.pets.isNotEmpty() }

    allPeopleHavePets shouldBe false
  }

  @Test
  fun howManyPeopleHaveCats(people: People) {
    val countOfPeopleHavingCats: Int = people.count { it.hasPet(PetType.CAT) }

    countOfPeopleHavingCats shouldBeExactly 2
  }

  @Test
  fun findMarySmith(people: People) {
    val person: Person = people.first { it.named("Mary Smith") }

    assertAll {
      test { person.firstName shouldBe "Mary" }
      test { person.lastName shouldBe "Smith" }
    }
  }

  @Test
  fun peopleWithPets(people: People) {
    val peopleWithPets: List<Person> = people.filter { it.pets.isNotEmpty() }

    peopleWithPets shouldHaveSize 7
  }

  @Test
  fun allPetTypesOfAllPeople(people: People) {
    val allPetTypes: Set<PetType> = people.flatMap { it.petTypes.keys }.toSet()

    allPetTypes shouldBe
        setOf(
            PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE)
  }

  @Test
  fun firstNamesOfAllPeople(people: People) {
    val firstNames: List<String> = people.map { it.firstName }

    firstNames shouldBe listOf("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John")
  }
}
