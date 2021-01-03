package com.example.pets.solutions

import com.example.pets.People
import com.example.pets.Person
import com.example.pets.PetType
import com.example.pets.fixtures.PetDomain
import com.example.pets.fixtures.dynamic
import data.Bag
import data.Multimap
import data.MutableMultimap
import data.MutableSetMultimap
import data.SetMultimap
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

        val counts: Bag<PetType> = people.flatMap { it.pets }.groupingBy { it.type }.eachCount()

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
        val lastNamesToPeople: MutableMultimap<String, Person> = mutableMapOf()
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

        val groupByLastName: Multimap<String, Person> = people.groupBy { it.lastName }
        test("There is 3 people named Smith") { groupByLastName.getValue("Smith") shouldHaveSize 3 }
      }

  @TestFactory
  fun peopleByTheirPets(people: People) =
      dynamic {
        // Frequent pattern
        val peopleByPetType: MutableSetMultimap<PetType, Person> = mutableMapOf()
        for (person in people) {
          for (pet in person.pets) {
            var peopleWithPetType = peopleByPetType[pet.type]
            if (peopleWithPetType == null) {
              peopleWithPetType = mutableSetOf()
              peopleByPetType[pet.type] = peopleWithPetType
            }
            peopleWithPetType.add(person)
          }
        }

        test("the count of people having cat") {
          peopleByPetType.getValue(PetType.CAT) shouldHaveSize 2
        }
        test("the count of people having dog") {
          peopleByPetType.getValue(PetType.DOG) shouldHaveSize 2
        }
        test("the count of people having hamster") {
          peopleByPetType.getValue(PetType.HAMSTER) shouldHaveSize 1
        }
        test("the count of people having turtle") {
          peopleByPetType.getValue(PetType.TURTLE) shouldHaveSize 1
        }
        test("the count of people having bird") {
          peopleByPetType.getValue(PetType.BIRD) shouldHaveSize 1
        }
        test("the count of people having snake") {
          peopleByPetType.getValue(PetType.SNAKE) shouldHaveSize 1
        }

        val distinctPeopleByPetType: SetMultimap<PetType, Person> =
            people.flatMap { it.petTypes.keys.map { p -> p to it } }
                .groupBy({ it.first }, { it.second })
                .mapValues { e -> e.value.toSet() }
        test("the count of people having cat") {
          distinctPeopleByPetType.getValue(PetType.CAT) shouldHaveSize 2
        }
        test("the count of people having dog") {
          distinctPeopleByPetType.getValue(PetType.DOG) shouldHaveSize 2
        }
        test("the count of people having hamster") {
          distinctPeopleByPetType.getValue(PetType.HAMSTER) shouldHaveSize 1
        }
        test("the count of people having turtle") {
          distinctPeopleByPetType.getValue(PetType.TURTLE) shouldHaveSize 1
        }
        test("the count of people having bird") {
          distinctPeopleByPetType.getValue(PetType.BIRD) shouldHaveSize 1
        }
        test("the count of people having snake") {
          distinctPeopleByPetType.getValue(PetType.SNAKE) shouldHaveSize 1
        }
      }
}
