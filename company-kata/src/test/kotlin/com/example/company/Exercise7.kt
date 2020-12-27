package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import data.Multimap
import data.getOrEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

typealias CityName = String

typealias ItemName = String

@ExtendWith(CompanyDomain::class)
class Exercise7 {

  @TestFactory
  fun customerByCity(company: Company): Iterable<DynamicTest> =
      dynamic {
        // TODO Create a Multimap where the keys are the names of cities and the values are the
        // Customers from those cities.
        val customersByCity: Multimap<CityName, Customer> = company { emptyMap() }

        val expectedLiphook = listOfNotNull(company.getCustomerNamed("Mary"))
        val expectedLondon =
            listOfNotNull(company.getCustomerNamed("Fred"), company.getCustomerNamed("Bill"))

        test("Libhook customer") { customersByCity["Liphook"] shouldBe expectedLiphook }
        test("London customer") { customersByCity["London"] shouldBe expectedLondon }
      }

  @Suppress("UNREACHABLE_CODE")
  @TestFactory
  fun itemsBySuppliers(company: Company): Iterable<DynamicTest> =
      dynamic {
        TODO(
            "Create a Multimap where the keys are the names of items and the values are the Suppliers that supply them.")
        val suppliersByItemNames: Multimap<ItemName, Supplier> = company { emptyMap() }

        test("2 suppliers for sofa") { suppliersByItemNames.getOrEmpty("sofa") shouldHaveSize 2 }
        test("3 suppliers for chair") { suppliersByItemNames.getOrEmpty("chair") shouldHaveSize 3 }
      }

  @Test
  fun refactorSetupCustomersAndOrders() {
    TODO(
        "Refactor CompanyDomain.setUpCustomersAndOrders() in the super class to not have so much repetition.")
  }
}
