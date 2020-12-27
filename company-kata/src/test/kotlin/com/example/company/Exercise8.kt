package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import data.Multimap
import data.SortedBag
import data.sortedBagOf
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise8 {

  @Suppress("UNREACHABLE_CODE")
  @TestFactory
  fun totalOrderValuesByCity(company: Company): Iterable<DynamicTest> =
      dynamic {
        TODO("Aggregate the total order values by city.")
        val totalOrderValuesByCity: Map<CityName, OrderValue> = company { emptyMap() }

        test("map size is 2") { totalOrderValuesByCity shouldHaveSize 2 }
        test("total order value of London") { totalOrderValuesByCity["London"] shouldBe 446.25 }
        test("total order value of Liphook") { totalOrderValuesByCity["Liphook"] shouldBe 857.0 }
      }

  @Suppress("UNREACHABLE_CODE")
  @TestFactory
  fun totalOrderValuesByItem(company: Company): Iterable<DynamicTest> =
      dynamic {
        TODO("Aggregate the total order values by item.")
        val totalOrderValuesByItem: Map<ItemName, OrderValue> = company { emptyMap() }

        test("map size is 12") { totalOrderValuesByItem shouldHaveSize 12 }
        test("total value of 'shed'") { totalOrderValuesByItem["shed"] shouldBe 100.0 }
        test("total value of 'cup'") { totalOrderValuesByItem["cup"] shouldBe 10.5 }
      }

  @Suppress("UNREACHABLE_CODE")
  @Test
  fun sortedOrders(company: Company) {
    TODO(
        "Find all customers' line item values greater than 7.5 and sort them by highest to lowest price.")
    val orderedPrices: SortedBag<Value> = company { sortedMapOf() }

    val expectedPrices =
        sortedBagOf(500.0, 150.0, 120.0, 75.0, 50.0, 50.0, 12.5) { left, right ->
          right.compareTo(left)
        }

    orderedPrices should beExactly(expectedPrices)
  }

  @Suppress("UNREACHABLE_CODE")
  @Test
  fun whoOrderedSaucers(company: Company) {
    TODO("Figure out which customers ordered saucers (in any of their orders).")
    val customersOrderedSaucers: List<Customer> = company { emptyList() }

    customersOrderedSaucers shouldHaveSize 2
  }

  @Suppress("UNREACHABLE_CODE", "UnusedUnaryOperator")
  @TestFactory
  fun mostExpensiveItem(company: Company): Iterable<DynamicTest> =
      dynamic {
        TODO(
            """
        Create a multimap where the values are customers and 
        the key is the price of the most expensive item that 
        the customer ordered.
        """.trimIndent())
        val mostExpensiveItemPriceByCustomer: Multimap<Value, Customer> = company { emptyMap() }

        // TODO count all customers from Multimap
        val allCustomerCount: Int = mostExpensiveItemPriceByCustomer.let { company { -1 } }

        test("value size is 3") { allCustomerCount shouldBe 3 }
        test("keys are 2") { mostExpensiveItemPriceByCustomer shouldHaveSize 2 }

        val expectedCustomers: List<Customer> =
            listOfNotNull(company.getCustomerNamed("Fred"), company.getCustomerNamed("Bill"))

        test("50.0 is Fred and Bill's most expensive item") {
          mostExpensiveItemPriceByCustomer[50.0] shouldBe expectedCustomers
        }
      }

  companion object {
    fun <T : Comparable<T>> beExactly(expected: SortedBag<T>): Matcher<SortedBag<T>> =
        object : Matcher<SortedBag<T>> {
          override fun test(value: SortedBag<T>): MatcherResult =
              object : MatcherResult {
                override fun failureMessage(): String =
                    "expected to equal exactly to: $expected\nbut actual is: $value"

                override fun negatedFailureMessage(): String =
                    "expected not to equal exactly to $expected"

                override fun passed(): Boolean {
                  if (value.size != expected.size) return false

                  val zip = expected.toList().zip(value.toList())
                  return zip.all { entry ->
                    val exp = entry.first
                    val act = entry.second
                    exp == act
                  }
                }
              }
        }
  }
}
