package com.example.pets.fixtures

import com.example.pets.Person

data class People(val items: MutableList<Person>) {
  operator fun <T> invoke(imitation: () -> T): T = imitation()
}
