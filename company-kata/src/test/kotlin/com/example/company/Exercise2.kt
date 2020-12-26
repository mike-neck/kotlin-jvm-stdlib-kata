package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import data.PartitionList
import data.emptyPartitionList
import data.rejected
import data.selected
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
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

  @Test
  fun doAnyCustomerLiveInLondon(company: Company) {
    val anyCustomerFromLondon: Boolean = company { false }

    anyCustomerFromLondon shouldBe true
  }

  @Test
  fun doAllCustomersLiveInLondon(company: Company) {
    val allCustomerFromLondon: Boolean = company { false }

    allCustomerFromLondon shouldBe true
  }

  @Test
  fun howManyCustomersLiveInLondon(company: Company) {
    val numberOfCustomerFromLondon: Int = company { 1000 }

    numberOfCustomerFromLondon shouldBeExactly 2
  }

  @Test
  fun customersWhoLiveInLondon(company: Company) {
    val customersFromLondon: List<Customer> = company { emptyList() }

    customersFromLondon shouldHaveSize 2
  }

  @Test
  fun customersWhoDontLiveInLondon(company: Company) {
    val customersNotFromLondon: List<Customer> = company { emptyList() }

    customersNotFromLondon shouldHaveSize 1
  }

  @TestFactory
  fun customersPartitionedByWhetherLivingInLondon(company: Company): Iterable<DynamicTest> {
    val partitionList: PartitionList<Customer> = company { emptyPartitionList() }

    return listOf(
        DynamicTest.dynamicTest("2 customers live in London") {
          partitionList.selected shouldHaveSize 2
        },
        DynamicTest.dynamicTest("A customer does not live in London") {
          partitionList.rejected shouldHaveSize 1
        })
  }

  @Test
  fun findMary(company: Company) {
    val mary: Customer? = company.getCustomerNamed("Mary")
    assertAll({ mary shouldNotBe null }, { mary?.name shouldBe "Mary" })
  }
}
