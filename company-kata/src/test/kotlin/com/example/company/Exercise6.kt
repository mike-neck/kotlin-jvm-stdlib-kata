package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise6 {

  @TestFactory
  fun sortedTotalOrderValue(company: Company): Iterable<DynamicTest> =
      dynamic {
        // TODO Get a list of the customers' total order values, sorted.
        val sortedTotalValues: List<Double> = company { emptyList() }

        test("The highest total order value should be 857.0") {
          sortedTotalValues.lastOrNull() shouldBe 857.0
        }
        test("The lowest total order value should be 71.0") {
          sortedTotalValues.firstOrNull() shouldBe 71.0
        }
      }

  @TestFactory
  fun sortedTotalOrderValueWithTypedArray(company: Company): Iterable<DynamicTest> =
      dynamic {
        // TODO Get a list of the customers' total order values, sorted.
        val sortedTotalValues: DoubleArray = company { doubleArrayOf() }

        test("The highest total order value should be 857.0") {
          sortedTotalValues.lastOrNull() shouldBe 857.0
        }
        test("The lowest total order value should be 71.0") {
          sortedTotalValues.firstOrNull() shouldBe 71.0
        }
      }

  @Test
  fun findMaximumTotalOrderValue(company: Company) {
    val maximumTotalOrderValue: Double? = company { null }

    maximumTotalOrderValue shouldBe 857.0
  }

  @Test
  fun findCustomerWithMaxTotalOrderValue(company: Company) {
    val customerWithMaxTotalOrderValue: Customer? = company { null }

    customerWithMaxTotalOrderValue shouldBe company.getCustomerNamed("Mary")
  }

  @Test
  fun supplierNamesAsTildeDelimitedString(company: Company) {
    val tildeSeparatedNames: String = company { "" }

    tildeSeparatedNames shouldBe
        "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins"
  }

  @Suppress("UNREACHABLE_CODE")
  @TestFactory
  fun deliverOrdersToLondon(company: Company): Iterable<DynamicTest> =
      dynamic {
        run { TODO("call deliver function onto orders by customers from London") }

        itemsAllSatisfy(
            "Fred's orders are all delivered", company.getCustomerNamed("Fred")?.orders) {
          it.delivered shouldBe true
        }
        itemsAllSatisfy(
            "Fred's orders are all delivered", company.getCustomerNamed("Mary")?.orders) {
          it.delivered shouldBe false
        }
        itemsAllSatisfy(
            "Fred's orders are all delivered", company.getCustomerNamed("Bill")?.orders) {
          it.delivered shouldBe true
        }
      }
}
