package com.example.pets

import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.assertAll
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise1 {

  // This method can be deleted
  @BeforeEach
  fun notNullIterable(people: People) {
    assertAll {
      test { people shouldNotBe null }
      test { people.items shouldHaveAtLeastSize 1 }
    }
  }

  @Test
  fun firstNamesOfAllPeople(people: People) {
    // Replace a block { mutableListOf() }, with a transformation method on Iterable(= people)
    val firstNames: List<String> = people { listOf() } // people.items...

    val expectedFirstNames: List<String> =
        listOf("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John")

    firstNames shouldBe expectedFirstNames
  }

  @Suppress("RedundantNullableReturnType")
  @Test
  fun namesOfMarySmithsPets(people: People) {
    // Find person having name "Mary Smith"
    val mayPerson: Person? =
        people {
          // You can know a person has name "May Smith" via named(String) function
          // val boolean: Boolean = person.named("Mary Smith")
          Person("", "", mutableListOf())
        }
    val person: Person = mayPerson ?: throw IllegalStateException("$mayPerson should be not null")

    val pets: List<Pet> = person.pets

    // Replace a block { mutableListOf() }, with a transformation method on MutableList()
    val names: List<String> = pets { mutableListOf() }

    names.joinToString() shouldBe "Tabby"
  }

  @Test
  fun peopleWithCats(people: People) {
    // Replace a block { mutableListOf() }, with a positive filtering method on MutableList(=people)
    val peopleWithCats: List<Person> = people { listOf() }

    peopleWithCats shouldHaveSize 2
  }

  @Test
  fun peopleWithoutCats(people: People) {
    // Replace a block { mutableListOf() }, with a negative filtering method on MutableList(=people)
    val peopleWithCats: List<Person> = people { listOf() }

    peopleWithCats shouldHaveSize 6
  }
}
