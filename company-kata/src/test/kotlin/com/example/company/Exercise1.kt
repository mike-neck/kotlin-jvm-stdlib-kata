package com.example.company

import com.example.company.CompanyDomain.Companion.invoke
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CompanyDomain::class)
class Exercise1 {

  @Test
  fun customerNames(company: Company) {
    val customerNames: List<String> = company { emptyList() }
    val expectedNames = listOf("Fred", "Mary", "Bill")

    customerNames shouldBe expectedNames
  }
}
