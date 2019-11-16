//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("DecadesKt")

package io.islandtime.measures

import io.islandtime.internal.DECADES_PER_CENTURY
import io.islandtime.internal.MONTHS_PER_DECADE
import io.islandtime.internal.YEARS_PER_DECADE
import io.islandtime.internal.timesExact
import io.islandtime.internal.toIntExact
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
inline class IntDecades(
  val value: Int
) : Comparable<IntDecades> {
  val absoluteValue: IntDecades
    get() = IntDecades(value.absoluteValue)
  val inMonths: IntMonths
    get() = (this.value * MONTHS_PER_DECADE).months

  val inYears: IntYears
    get() = (this.value * YEARS_PER_DECADE).years

  val inCenturies: IntCenturies
    get() = (this.value / DECADES_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0

  fun isNegative(): Boolean = value < 0

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntDecades): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0Y"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue timesExact 10)
          append('Y')
      }
  }
  fun inMonthsExact() = (this.value timesExact MONTHS_PER_DECADE).months

  fun inYearsExact() = (this.value timesExact YEARS_PER_DECADE).years

  operator fun unaryMinus() = IntDecades(-value)

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun plus(months: LongMonths) = this.toLong().inMonths + months

  operator fun plus(years: IntYears) = this.inYears + years

  operator fun plus(years: LongYears) = this.toLong().inYears + years

  operator fun plus(decades: IntDecades) = IntDecades(this.value + decades.value)

  operator fun plus(decades: LongDecades) = LongDecades(this.value.toLong() + decades.value)

  operator fun plus(centuries: IntCenturies) = this + centuries.inDecades

  operator fun plus(centuries: LongCenturies) = this.toLong() + centuries.inDecades

  operator fun minus(months: IntMonths) = plus(-months)

  operator fun minus(months: LongMonths) = plus(-months)

  operator fun minus(years: IntYears) = plus(-years)

  operator fun minus(years: LongYears) = plus(-years)

  operator fun minus(decades: IntDecades) = plus(-decades)

  operator fun minus(decades: LongDecades) = plus(-decades)

  operator fun minus(centuries: IntCenturies) = plus(-centuries)

  operator fun minus(centuries: LongCenturies) = plus(-centuries)

  operator fun times(scalar: Int) = IntDecades(this.value * scalar)

  operator fun times(scalar: Long) = this.toLong() * scalar

  operator fun div(scalar: Int) = IntDecades(this.value / scalar)

  operator fun div(scalar: Long) = this.toLong() / scalar

  operator fun rem(scalar: Int) = IntDecades(this.value % scalar)

  operator fun rem(scalar: Long) = this.toLong() % scalar

  inline fun <T> toComponents(action: (centuries: IntCenturies, decades: IntDecades) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries)
    return action(centuries, decades)
  }

  fun toLong() = LongDecades(this.value.toLong())

  companion object {
    val MIN: IntDecades = IntDecades(Int.MIN_VALUE)

    val MAX: IntDecades = IntDecades(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongDecades(
  val value: Long
) : Comparable<LongDecades> {
  val absoluteValue: LongDecades
    get() = LongDecades(value.absoluteValue)
  val inMonths: LongMonths
    get() = (this.value * MONTHS_PER_DECADE).months

  val inYears: LongYears
    get() = (this.value * YEARS_PER_DECADE).years

  val inCenturies: LongCenturies
    get() = (this.value / DECADES_PER_CENTURY).centuries

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongDecades): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0Y"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue timesExact 10)
          append('Y')
      }
  }
  fun inMonthsExact() = (this.value timesExact MONTHS_PER_DECADE).months

  fun inYearsExact() = (this.value timesExact YEARS_PER_DECADE).years

  operator fun unaryMinus() = LongDecades(-value)

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun plus(months: LongMonths) = this.inMonths + months

  operator fun plus(years: IntYears) = this.inYears + years

  operator fun plus(years: LongYears) = this.inYears + years

  operator fun plus(decades: IntDecades) = LongDecades(this.value + decades.value)

  operator fun plus(decades: LongDecades) = LongDecades(this.value + decades.value)

  operator fun plus(centuries: IntCenturies) = this + centuries.inDecades

  operator fun plus(centuries: LongCenturies) = this + centuries.inDecades

  operator fun minus(months: IntMonths) = plus(-months)

  operator fun minus(months: LongMonths) = plus(-months)

  operator fun minus(years: IntYears) = plus(-years)

  operator fun minus(years: LongYears) = plus(-years)

  operator fun minus(decades: IntDecades) = plus(-decades)

  operator fun minus(decades: LongDecades) = plus(-decades)

  operator fun minus(centuries: IntCenturies) = plus(-centuries)

  operator fun minus(centuries: LongCenturies) = plus(-centuries)

  operator fun times(scalar: Int) = LongDecades(this.value * scalar)

  operator fun times(scalar: Long) = LongDecades(this.value * scalar)

  operator fun div(scalar: Int) = LongDecades(this.value / scalar)

  operator fun div(scalar: Long) = LongDecades(this.value / scalar)

  operator fun rem(scalar: Int) = LongDecades(this.value % scalar)

  operator fun rem(scalar: Long) = LongDecades(this.value % scalar)

  inline fun <T> toComponents(action: (centuries: LongCenturies, decades: IntDecades) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries).toInt()
    return action(centuries, decades)
  }

  fun toInt() = IntDecades(this.value.toInt())

  fun toIntExact() = IntDecades(this.value.toIntExact())

  companion object {
    val MIN: LongDecades = LongDecades(Long.MIN_VALUE)

    val MAX: LongDecades = LongDecades(Long.MAX_VALUE)
  }
}

val Int.decades: IntDecades
  get() = IntDecades(this)

val Long.decades: LongDecades
  get() = LongDecades(this)
