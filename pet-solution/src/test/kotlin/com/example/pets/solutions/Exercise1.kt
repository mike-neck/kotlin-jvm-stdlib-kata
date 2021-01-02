package com.example.pets.solutions

import com.example.pets.People
import com.example.pets.Person
import com.example.pets.Pet
import com.example.pets.PetType
import com.example.pets.fixtures.PetDomain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(PetDomain::class)
class Exercise1 {

  @Test
  fun firstNamesOfAllPeople(people: People) {
    val firstNames: List<String> = people.map { it.firstName }

    val expectedFirstNames: List<String> =
        listOf("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John")

    firstNames shouldBe expectedFirstNames
  }

  @Suppress("RedundantNullableReturnType")
  @Test
  fun namesOfMarySmithsPets(people: People) {
    val mayPerson: Person? = people.find { it.named("Mary Smith") }
    val person: Person = mayPerson ?: throw IllegalStateException("$mayPerson should be not null")

    val pets: List<Pet> = person.pets

    val names: List<String> = pets.map { it.name }

    names.joinToString() shouldBe "Tabby"
  }

  @Test
  fun peopleWithCats(people: People) {
    val peopleWithCats: List<Person> = people.filter { it.hasPet(PetType.CAT) }

    peopleWithCats shouldHaveSize 2
  }

  @Test
  fun peopleWithoutCats(people: People) {
    val peopleWithCats: List<Person> = people.filterNot { it.hasPet(PetType.CAT) }

    peopleWithCats shouldHaveSize 6
  }
}
