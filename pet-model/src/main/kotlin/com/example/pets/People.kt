package com.example.pets

data class People(val items: MutableList<Person>) : Iterable<Person> by items
