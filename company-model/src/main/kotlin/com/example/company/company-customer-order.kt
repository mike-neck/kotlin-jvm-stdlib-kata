package com.example.company

data class LineItem(val name: String, val value: Double)

data class Order(
    val orderNumber: Int,
    var delivered: Boolean = false,
    val lineItems: MutableList<LineItem> = mutableListOf()
) {
  fun deliver() = Unit.apply { delivered = true }

  fun addLineItem(lineItem: LineItem) {
    lineItems.add(lineItem)
  }

  val value: Double
    get() =
        lineItems
            .filter { TODO("remove this line and refactor to use sumOf {}") }
            .map { it.value }
            .reduce { acc, item -> acc + item }
}

data class Customer(
    val name: String, val city: String, val orders: MutableList<Order> = mutableListOf()
) {
  fun addOrder(order: Order): Unit = Unit.apply { orders.add(order) }

  val totalOrderValue: Double
    get() = orders.sumOf { it.value }
}

class Supplier(
    val name: String,
    // TODO Refactor this Array into MutableList
    val itemNames: Array<String>
) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Supplier) return false

    if (name != other.name) return false
    if (!itemNames.contentEquals(other.itemNames)) return false

    return true
  }

  override fun hashCode(): Int {
    var result = name.hashCode()
    result = 31 * result + itemNames.contentHashCode()
    return result
  }

  override fun toString(): String {
    return "Supplier(name='$name', itemNames=${itemNames.contentToString()})"
  }
}

data class Company(val name: String, val customers: MutableList<Customer> = mutableListOf()) {

  // TODO Refactor this array based implementation to a MutableList based one.
  // TODO Refactor this variable to final value(e.g. var -> val).
  var suppliers: Array<Supplier> = arrayOf()

  fun addCustomer(customer: Customer): Unit = Unit.apply { customers.add(customer) }

  @Suppress("UNREACHABLE_CODE")
  val orders: List<Order>
    get() {
      TODO(
          """
      Refactor this code to use kotlin collections API
      Make result list not to be affected by changes to the original customer orders
      """.trimIndent())
      val mutableList = mutableListOf<Order>()
      for (customer in customers) {
        mutableList.addAll(customer.orders)
      }
      return mutableList
    }

  val mostRecentCustomer: Customer
    get() = customers.last()

  fun addSupplier(supplier: Supplier) {
    val current = this.suppliers
    this.suppliers = current + supplier
  }

  @Suppress("UNREACHABLE_CODE")
  fun getCustomerNamed(name: String): Customer? {
    TODO("""
      Refactor null to appropriate implementation.
    """.trimIndent())
    return null
  }
}
