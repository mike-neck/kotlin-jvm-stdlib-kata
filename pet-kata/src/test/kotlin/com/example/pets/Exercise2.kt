package com.example.pets

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
    // Replace { false } block, with a any function call on `People.items`
    val anySatisfy: Boolean = people { false }

    anySatisfy shouldBe true
  }

  @Test
  fun doAllPeopleHavePets(people: People) {
    // Replace { true } block, with a method call send to people that checks if all people have pets
    val allPeopleHavePets: Boolean = people { true }

    allPeopleHavePets shouldBe false
  }

  @Test
  fun howManyPeopleHaveCats(people: People) {
    // Replace { 100 } block, with a method call to count the number of people having cats.
    val countOfPeopleHavingCats: Int = people { 100 }

    countOfPeopleHavingCats shouldBeExactly 2
  }

  @Test
  fun findMarySmith(people: People) {
    // Replace { Person() } block, with a method call on `people` to find 'Mary Smith'
    val person: Person = people { Person() }

    assertAll {
      test { person.firstName shouldBe "Mary" }
      test { person.lastName shouldBe "Smith" }
    }
  }

  @Test
  fun peopleWithPets(people: People) {
    // Replace { listOf } block, with a method call on `people` to find people owning pets
    val peopleWithPets: List<Person> = people { listOf() }

    peopleWithPets shouldHaveSize 7
  }

  @Test
  fun allPetTypesOfAllPeople(people: People) {
    // Replace { setOf } block, with a method call on `people` of mapping to distinct `PetType`.
    val allPetTypes: Set<PetType> = people { setOf() }

    allPetTypes shouldBe
        setOf(
            PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE)
  }

  @Test
  fun firstNamesOfAllPeople(people: People) {
    // Transform people into a list of first names
    val firstNames: List<String> = people { listOf() }

    firstNames shouldBe listOf("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John")
  }
}
