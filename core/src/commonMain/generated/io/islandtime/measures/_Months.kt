//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("MonthsKt")

package io.islandtime.measures

import io.islandtime.internal.MONTHS_PER_CENTURY
import io.islandtime.internal.MONTHS_PER_DECADE
import io.islandtime.internal.MONTHS_PER_YEAR
import kotlin.Boolean
import kotlin.Comparable
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntMonths(
  val value: Int
) : Comparable<IntMonths> {
  val inYears: IntYears
    get() = (this.value / MONTHS_PER_YEAR).years

  val inDecades: IntDecades
    get() = (this.value / MONTHS_PER_DECADE).decades

  val inCenturies: IntCenturies
    get() = (this.value / MONTHS_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0

  fun isNegative(): Boolean = value < 0

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntMonths): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0M"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('M')
      }
  }
  companion object {
    val MIN: IntMonths = IntMonths(Int.MIN_VALUE)

    val MAX: IntMonths = IntMonths(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongMonths(
  val value: Long
) : Comparable<LongMonths> {
  val inYears: LongYears
    get() = (this.value / MONTHS_PER_YEAR).years

  val inDecades: LongDecades
    get() = (this.value / MONTHS_PER_DECADE).decades

  val inCenturies: LongCenturies
    get() = (this.value / MONTHS_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongMonths): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0M"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('M')
      }
  }
  companion object {
    val MIN: LongMonths = LongMonths(Long.MIN_VALUE)

    val MAX: LongMonths = LongMonths(Long.MAX_VALUE)
  }
}

val Int.months: IntMonths
  get() = IntMonths(this)

val Long.months: LongMonths
  get() = LongMonths(this)