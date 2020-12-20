package com.example.pets.fixtures

class AssertAll(internal val tests: MutableList<() -> Unit> = mutableListOf()) {
  fun test(executable: () -> Unit): Unit = tests.add(executable).let {}
}

fun assertAll(configure: AssertAll.() -> Unit): Unit =
    org.junit.jupiter.api.assertAll(AssertAll().also(configure).tests.toList())
