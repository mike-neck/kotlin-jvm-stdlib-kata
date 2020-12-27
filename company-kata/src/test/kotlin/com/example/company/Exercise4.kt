package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise4 {

  @Test
  fun findSupplierNames(company: Company) {
    val supplierNames: Array<String> = company { arrayOf() }

    val expectedNames: Array<String> =
        arrayOf(
            "Shedtastic",
            "Splendid Crocks",
            "Annoying Pets",
            "Gnomes 'R' Us",
            "Furniture Hamlet",
            "SFD",
            "Doxins")

    supplierNames shouldBe expectedNames
  }

  @Test
  fun countSuppliersWithMoreThanTwoItems(company: Company) {
    val predicate: (Supplier) -> Boolean =
        { TODO("implement predicate[Supplier more than 2 items]") }

    // Find the number of suppliers that satisfy the above predicate.
    val countOfSuppliersWithMoreThan2Items: Int = company { 200.also { TODO("use predicate") } }

    countOfSuppliersWithMoreThan2Items shouldBe 5
  }

  /**
   * Try to solve this without changing the return type of [com.example.company.Supplier.itemNames].
   */
  @Test
  fun whoSuppliesSandwichToaster(company: Company) {
    // Create a Predicate that will check to see if a Supplier supplies a "sandwich toaster".
    val predicate: (Supplier) -> Boolean =
        { TODO("implement predicate[Supplier supplies a 'sandwich toaster']") }

    // Find one supplier that supplies toasters.
    val toasterSupplier: Supplier? = company { null }

    assertAll({ toasterSupplier shouldNotBe null }, { toasterSupplier?.name shouldBe "Doxins" })
  }

  @Suppress("UNREACHABLE_CODE")
  @Test
  fun filterOrderValues(company: Company) {
    TODO("Get the order values of the most recent customer that are greater than 1.5.")
    val orders: List<Order> = company.mostRecentCustomer.orders
    val orderValues: List<Double> = orders.let { company { emptyList() } }
    val filteredOrderValues: List<Double> = orderValues.let { company { emptyList() } }

    val expectedOrderValues = listOf(372.5, 1.75)

    filteredOrderValues shouldBe expectedOrderValues
  }

  @Suppress("UNREACHABLE_CODE")
  @Test
  fun filterOrderValuesUsingDoubleArray(company: Company) {
    TODO(
        "Get the order values of the most recent customer that are greater than 1.5 without changing variable types declared in the following code.")
    val orders: List<Order> = company.mostRecentCustomer.orders
    val orderValues: DoubleArray = orders.let { company { doubleArrayOf() } }
    val filteredOrderValues: DoubleArray = orderValues.let { company { doubleArrayOf() } }

    val expectedOrderValues = doubleArrayOf(372.5, 1.75)

    filteredOrderValues shouldBe expectedOrderValues
  }

  @Suppress("UNREACHABLE_CODE")
  @Test
  fun filterOrders(company: Company) {
    TODO(
        "Get the actual orders (not their double values) where those orders have a value greater than 2.0.")
    val orders: List<Order> = company.mostRecentCustomer.orders
    val filteredOrders: List<Order> = orders.let { company { emptyList() } }

    val expectedOrders: List<Order> = company.mostRecentCustomer.orders.take(2)

    filteredOrders shouldBe expectedOrders
  }
}
