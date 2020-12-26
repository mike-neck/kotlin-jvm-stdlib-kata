package com.example.company

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class Exercise2 {

  @TestFactory
  fun customerFromLondonPredicate(): Iterable<DynamicTest> {
    val predicateThatTestsCustomerFromLondon: (Customer) -> Boolean = { true }

    val customerFromLondon = Customer("customer from London", "London")
    val customerFromNewYork = Customer("customer from NY", "NewYork")

    return listOf(
        DynamicTest.dynamicTest("customer from London comes from London") {
          predicateThatTestsCustomerFromLondon(customerFromLondon) shouldBe true
        },
        DynamicTest.dynamicTest("customer from NY comes not from London") {
          predicateThatTestsCustomerFromLondon(customerFromNewYork) shouldBe false
        })
  }
}
