package com.example.pets.fixtures

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.fail

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

  fun failAll(memo: String): Unit = beforeCallbacks.add { fail("$memo[$it]") }.let {}
}

inline fun dynamic(configure: DynamicTestFactory.() -> Unit): Iterable<DynamicTest> =
    DynamicTestFactory().also(configure).map {
      DynamicTest.dynamicTest(it.title) { it.executable() }
    }
