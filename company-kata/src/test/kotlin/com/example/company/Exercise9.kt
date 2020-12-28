package com.example.company

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise9 {

  data class FlatOrderItem(
      val companyName: String,
      val customerName: String,
      val customerCity: CityName,
      val orderNumber: Int,
      val orderDelivered: Boolean,
      val lineItemName: ItemName,
      val lineItemValue: Value)

  companion object {
    private val flatOrderItems =
        listOf(
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "shed", 50.0),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "saucer", 1.0),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "saucer", 1.0),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "saucer", 1.0),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "chair", 12.5),
            FlatOrderItem("Bloggs Shed Supplies", "Fred", "London", 1, false, "table", 1.0),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "cat", 150.0),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "big shed", 500.0),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "cup", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "saucer", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "saucer", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "saucer", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "saucer", 1.5),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "sofa", 120.0),
            FlatOrderItem("Bloggs Shed Supplies", "Mary", "Liphook", 2, false, "dog", 75.0),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "shed", 50.0),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 3, false, "gnome", 7.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 4, false, "bowl", 1.25),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 4, false, "goldfish", 0.5),
            FlatOrderItem("Bloggs Shed Supplies", "Bill", "London", 5, false, "table", 1.0))
  }

  @TestFactory
  fun loadDataFromCsv(company: Company): Iterable<DynamicTest> =
      dynamic {
        val actual = convertIterableOfFlatOrderItemsIntoCompany(flatOrderItems)

        test("company name") { actual.name shouldBe company.name }
        test("customers") { actual.customers shouldBe company.customers }
        test("all orders") { actual.orders shouldBe company.orders }
      }

  private fun convertIterableOfFlatOrderItemsIntoCompany(items: Iterable<FlatOrderItem>): Company {
    TODO("implement me")
  }
}
