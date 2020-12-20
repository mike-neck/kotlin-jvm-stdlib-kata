package com.example.pets.fixtures

import com.example.pets.Person

data class People(val items: MutableList<Person>) : Iterable<Person> by items
