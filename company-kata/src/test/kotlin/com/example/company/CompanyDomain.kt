package com.example.company

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.junit.jupiter.api.fail

class CompanyDomain : ParameterResolver {

  companion object {
    operator fun <T> Company.invoke(f: () -> T): T = f()
  }

  private fun testData(): Company {
    val company = Company("Bloggs Shed Supplies")

    company.suppliers {
      supplier {
        name = "Shedtastic"
        itemNames("shed", "big shed", "huge shed")
      }
      supplier {
        name = "Splendid Crocks"
        itemNames("cup", "saucer", "bowl")
      }
      supplier {
        name = "Annoying Pets"
        itemNames("dog", "cat", "goldfish")
      }
      supplier {
        name = "Gnomes 'R' Us"
        itemNames("gnome")
      }
      supplier {
        name = "Furniture Hamlet"
        itemNames("table", "sofa", "chair")
      }
      supplier {
        name = "SFD"
        itemNames("sofa", "chair")
      }
      supplier {
        name = "Doxins"
        itemNames("kettle", "plasma screen", "sandwich toaster")
      }
    }

    val ids: IntArray = intArrayOf(0)
    val generateNewId: () -> Int = { ++ids[0] }

    // TODO 7: Refactor Order and its API/DSL so that this repetition is not necessary.
    setupCustomerAndOrders {
      val fredOrder = Order(generateNewId())
      fredOrder.addLineItem(LineItem("shed", 50.0))

      // TODO 7: Add 3 cups at 1.5 each to the order
      fredOrder.addLineItem(LineItem("cup", 1.5))
      fredOrder.addLineItem(LineItem("cup", 1.5))
      fredOrder.addLineItem(LineItem("cup", 1.5))

      // TODO 7: Add 3 saucers at 1.0 each to the order
      fredOrder.addLineItem(LineItem("saucer", 1.0))
      fredOrder.addLineItem(LineItem("saucer", 1.0))
      fredOrder.addLineItem(LineItem("saucer", 1.0))

      fredOrder.addLineItem(LineItem("chair", 12.50))
      fredOrder.addLineItem(LineItem("table", 1.0))

      val fred = Customer("Fred", "London")
      fred.addOrder(fredOrder)
      company.addCustomer(fred)
    }

    setupCustomerAndOrders {
      val maryOrder = Order(generateNewId())
      maryOrder.addLineItem(LineItem("cat", 150.0))
      maryOrder.addLineItem(LineItem("big shed", 500.0))

      // TODO 7: Add 4 cups at 1.50 each to the order
      maryOrder.addLineItem(LineItem("cup", 1.5))
      maryOrder.addLineItem(LineItem("cup", 1.5))
      maryOrder.addLineItem(LineItem("cup", 1.5))
      maryOrder.addLineItem(LineItem("cup", 1.5))

      // TODO 7: Add 4 saucers at 1.50 each to the order
      maryOrder.addLineItem(LineItem("saucer", 1.5))
      maryOrder.addLineItem(LineItem("saucer", 1.5))
      maryOrder.addLineItem(LineItem("saucer", 1.5))
      maryOrder.addLineItem(LineItem("saucer", 1.5))

      maryOrder.addLineItem(LineItem("sofa", 120.0))
      maryOrder.addLineItem(LineItem("dog", 75.0))

      val mary = Customer("Mary", "Liphook")
      mary.addOrder(maryOrder)
      company.addCustomer(mary)
    }

    setupCustomerAndOrders {
      val bill = Customer("Bill", "London")
      setupCustomerAndOrders {
        val order = Order(generateNewId())
        order.addLineItem(LineItem("shed", 50.0))
        // TODO 7: Add 43 gnomes at 7.50 each to the order
        repeat(43) { order.addLineItem(LineItem("gnome", 7.50)) }
        bill.addOrder(order)
      }
      setupCustomerAndOrders {
        val order = Order(generateNewId())
        order.addLineItem(LineItem("bowl", 1.25))
        order.addLineItem(LineItem("goldfish", 0.50))
        bill.addOrder(order)
      }
      setupCustomerAndOrders {
        val order = Order(generateNewId())
        order.addLineItem(LineItem("table", 1.0))
        bill.addOrder(order)
      }
      company.addCustomer(bill)
    }
    return company
  }

  private fun Company.suppliers(config: CompanySuppliersConfigurer.() -> Unit) {
    val configurer = CompanySuppliersConfigurer(this)
    configurer.config()
  }

  // This function does nothing, but separates code block from others.
  private fun setupCustomerAndOrders(setup: () -> Unit): Unit = setup()

  override fun supportsParameter(
      parameterContext: ParameterContext?, extensionContext: ExtensionContext?
  ): Boolean = requireNotNull(parameterContext).parameter.type == Company::class.java

  override fun resolveParameter(
      parameterContext: ParameterContext?, extensionContext: ExtensionContext?
  ): Company = testData()
}

private class CompanySuppliersConfigurer(private val company: Company) {
  fun supplier(config: SupplierBuilder.() -> Unit) {
    val builder = SupplierBuilder()
    builder.config()
    company.addSupplier(builder.build())
  }
}

private class SupplierBuilder(
    var name: String? = null, var itemNames: MutableList<String> = mutableListOf()
) {
  fun itemNames(vararg itemNames: String) {
    this.itemNames.addAll(itemNames.toList())
  }

  private fun illegalArgumentException(param: String): IllegalArgumentException =
      IllegalArgumentException("illegal $param is given, [$name, $itemNames]")

  fun build(): Supplier =
      Supplier(
          name = this.name ?: throw IllegalArgumentException("name"),
          itemNames = this.itemNames.toTypedArray())
}

class DynamicTestItem(val title: String, val executable: () -> Unit)

class DynamicTestFactory(
    private val items: MutableList<DynamicTestItem> = mutableListOf(),
    private val beforeCallbacks: MutableList<(String) -> Unit> = mutableListOf()
) : Iterable<DynamicTestItem> by items {
  fun test(title: String, executable: () -> Unit): Unit =
      items.add(
              DynamicTestItem(title) {
                beforeCallbacks.forEach { it(title) }
                executable()
              })
          .let {}

  fun <T : Any> itemsAllSatisfy(
      title: String, targets: Iterable<T>?, executable: (T) -> Unit
  ): Unit =
      if (targets == null) test(title) { fail { "targets do not satisfy anything $title" } }
      else
          targets.forEachIndexed { index, target -> test("$title - $index") { executable(target) } }

  fun failAll(memo: String): Unit = beforeCallbacks.add { fail("$memo[$it]") }.let {}
}

inline fun dynamic(configure: DynamicTestFactory.() -> Unit): Iterable<DynamicTest> =
    DynamicTestFactory().also(configure).map {
      DynamicTest.dynamicTest(it.title) { it.executable() }
    }
