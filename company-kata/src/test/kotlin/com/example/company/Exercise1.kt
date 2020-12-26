package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise1 {

  @Test
  fun customerNames(company: Company) {
    val customerNames: List<String> = company { emptyList() }
    val expectedNames = listOf("Fred", "Mary", "Bill")

    customerNames shouldBe expectedNames
  }

  @Test
  fun customerCities(company: Company) {
    val customerCities: List<String> = company { emptyList() }
    val expectedCities = listOf("London", "Liphook", "London")

    customerCities shouldBe expectedCities
  }

  @Test
  fun londonCustomers(company: Company) {
    val customersFromLondon: List<Customer> = company { emptyList() }

    customersFromLondon shouldHaveSize 2
  }
}
