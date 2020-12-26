package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail

@ExtendWith(CompanyDomain::class)
class Exercise3 {

  @Test
  fun improveGetOrders(company: Company) {
    fail { "Improve getOrders without breaking this test" }
    company.orders shouldHaveSize 5
  }

  @Test
  fun findItemNames(company: Company) {
    val allOrderedLineItems: List<LineItem> = company { emptyList() }
    val actualItemNames: Set<String> = company { emptySet() }

    val expectedItemNames =
        setOf(
            "shed",
            "big shed",
            "bowl",
            "cat",
            "cup",
            "chair",
            "dog",
            "goldfish",
            "gnome",
            "saucer",
            "sofa",
            "table")

    actualItemNames shouldBe expectedItemNames
  }

  @Test
  fun findCustomerNames(company: Company) {
    val customerNames: List<String> = company { emptyList() }

    val expectedNames = listOf("Fred", "Mary", "Bill")

    customerNames shouldBe expectedNames
  }
}
