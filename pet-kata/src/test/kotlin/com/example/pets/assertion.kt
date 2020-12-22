package com.example.pets

import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult

fun Double.withDelta(delta: Double): Matcher<Double> =
    object : Matcher<Double> {
      override fun test(value: Double): MatcherResult =
          object : MatcherResult {

            override fun failureMessage(): String =
                "$value does not equal to $this@withDelta within delta $delta"

            override fun negatedFailureMessage(): String =
                "$value equals to $this@withDelta within delta $delta"

            override fun passed(): Boolean =
                this@withDelta - delta <= value && value <= this@withDelta + delta
          }
    }
