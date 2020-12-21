package com.example.pets.fixtures

import org.junit.jupiter.api.DynamicTest

class DynamicTestItem(val title: String, val executable: () -> Unit)

class DynamicTestFactory(internal val items: MutableList<DynamicTestItem> = mutableListOf()) :
    Iterable<DynamicTestItem> by items {
  fun test(title: String, executable: () -> Unit): Unit =
      items.add(DynamicTestItem(title, executable)).let {}
}

fun dynamic(configure: DynamicTestFactory.() -> Unit): Iterable<DynamicTest> =
    DynamicTestFactory().also(configure).map {
      DynamicTest.dynamicTest(it.title) { it.executable() }
    }
