package com.example.pets

import java.util.stream.*

data class People(val items: MutableList<Person>) : Iterable<Person> by items {
  fun stream(): Stream<Person> = this.toList().stream()
}
