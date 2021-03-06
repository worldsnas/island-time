//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("MonthsKt")

package io.islandtime.measures

import io.islandtime.internal.MONTHS_PER_CENTURY
import io.islandtime.internal.MONTHS_PER_DECADE
import io.islandtime.internal.MONTHS_PER_YEAR
import io.islandtime.internal.minusExact
import io.islandtime.internal.negateExact
import io.islandtime.internal.plusExact
import io.islandtime.internal.timesExact
import io.islandtime.internal.toIntExact
import kotlin.Boolean
import kotlin.Comparable
import kotlin.Int
import kotlin.Long
import kotlin.PublishedApi
import kotlin.String
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

/**
 * A number of months.
 */
inline class IntMonths(
  /**
   * The underlying value.
   */
  val value: Int
) : Comparable<IntMonths> {
  /**
   * Returns the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: IntMonths
    get() = if (value < 0) -this else this
  /**
   * Convert to whole years.
   */
  val inYears: IntYears
    get() = (value / MONTHS_PER_YEAR).years

  /**
   * Convert to whole decades.
   */
  val inDecades: IntDecades
    get() = (value / MONTHS_PER_DECADE).decades

  /**
   * Convert to whole centuries.
   */
  val inCenturies: IntCenturies
    get() = (value / MONTHS_PER_CENTURY).centuries

  /**
   * Is this duration zero?
   */
  fun isZero(): Boolean = value == 0

  /**
   * Is this duration negative?
   */
  fun isNegative(): Boolean = value < 0

  /**
   * Is this duration positive?
   */
  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntMonths): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when (value) {
       0 -> "P0M"
       Int.MIN_VALUE -> "-P2147483648M"
       else -> buildString {
         if (value < 0) { append('-') }
         append("P")
         append(value.absoluteValue)
         append('M')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = IntMonths(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = IntMonths(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = IntMonths(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = this.toLongMonths() * scalar

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): IntMonths {
     return if (scalar == -1) {
       -this
     } else {
       IntMonths(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if the scalar is zero
   */
  operator fun div(scalar: Long): LongMonths = this.toLongMonths() / scalar
  operator fun rem(scalar: Int) = IntMonths(value % scalar)

  operator fun rem(scalar: Long) = this.toLongMonths() % scalar

  operator fun plus(months: IntMonths) = IntMonths(value plusExact months.value)

  operator fun minus(months: IntMonths) = IntMonths(value minusExact months.value)

  operator fun plus(months: LongMonths) = LongMonths(value.toLong() plusExact months.value)

  operator fun minus(months: LongMonths) = LongMonths(value.toLong() minusExact months.value)

  operator fun plus(years: IntYears) = this + years.inMonths

  operator fun minus(years: IntYears) = this - years.inMonths

  operator fun plus(years: LongYears) = this.toLongMonths() + years.inMonths

  operator fun minus(years: LongYears) = this.toLongMonths() - years.inMonths

  operator fun plus(decades: IntDecades) = this + decades.inMonths

  operator fun minus(decades: IntDecades) = this - decades.inMonths

  operator fun plus(decades: LongDecades) = this.toLongMonths() + decades.inMonths

  operator fun minus(decades: LongDecades) = this.toLongMonths() - decades.inMonths

  operator fun plus(centuries: IntCenturies) = this + centuries.inMonths

  operator fun minus(centuries: IntCenturies) = this - centuries.inMonths

  operator fun plus(centuries: LongCenturies) = this.toLongMonths() + centuries.inMonths

  operator fun minus(centuries: LongCenturies) = this.toLongMonths() - centuries.inMonths

  inline fun <T> toComponents(action: (years: IntYears, months: IntMonths) -> T): T {
    val years = (value / MONTHS_PER_YEAR).years
    val months = (value % MONTHS_PER_YEAR).months
    return action(years, months)
  }

  inline fun <T> toComponents(action: (
    decades: IntDecades,
    years: IntYears,
    months: IntMonths
  ) -> T): T {
    val decades = (value / MONTHS_PER_DECADE).decades
    val years = ((value % MONTHS_PER_DECADE) / MONTHS_PER_YEAR).years
    val months = (value % MONTHS_PER_YEAR).months
    return action(decades, years, months)
  }

  inline fun <T> toComponents(action: (
    centuries: IntCenturies,
    decades: IntDecades,
    years: IntYears,
    months: IntMonths
  ) -> T): T {
    val centuries = (value / MONTHS_PER_CENTURY).centuries
    val decades = ((value % MONTHS_PER_CENTURY) / MONTHS_PER_DECADE).decades
    val years = ((value % MONTHS_PER_DECADE) / MONTHS_PER_YEAR).years
    val months = (value % MONTHS_PER_YEAR).months
    return action(centuries, decades, years, months)
  }

  /**
   * Convert to [LongMonths].
   */
  fun toLongMonths() = LongMonths(value.toLong())

  /**
   * Convert to a unit-less `Long` value.
   */
  fun toLong() = value.toLong()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: IntMonths = IntMonths(Int.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: IntMonths = IntMonths(Int.MAX_VALUE)
  }
}

/**
 * Convert to [IntMonths].
 */
val Int.months: IntMonths
  get() = IntMonths(this)

/**
 * Multiply by a number of months.
 * @throws ArithmeticException if overflow occurs
 */
operator fun Int.times(months: IntMonths) = months * this

/**
 * Multiply by a number of months.
 * @throws ArithmeticException if overflow occurs
 */
operator fun Long.times(months: IntMonths) = months * this

/**
 * A number of months.
 */
inline class LongMonths(
  /**
   * The underlying value.
   */
  val value: Long
) : Comparable<LongMonths> {
  /**
   * Returns the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: LongMonths
    get() = if (value < 0) -this else this
  /**
   * Convert to whole years.
   */
  val inYears: LongYears
    get() = (value / MONTHS_PER_YEAR).years

  /**
   * Convert to whole decades.
   */
  val inDecades: LongDecades
    get() = (value / MONTHS_PER_DECADE).decades

  /**
   * Convert to whole centuries.
   */
  val inCenturies: LongCenturies
    get() = (value / MONTHS_PER_CENTURY).centuries

  /**
   * Is this duration zero?
   */
  fun isZero(): Boolean = value == 0L

  /**
   * Is this duration negative?
   */
  fun isNegative(): Boolean = value < 0L

  /**
   * Is this duration positive?
   */
  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongMonths): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when (value) {
       0L -> "P0M"
       Long.MIN_VALUE -> "-P9223372036854775808M"
       else -> buildString {
         if (value < 0) { append('-') }
         append("P")
         append(value.absoluteValue)
         append('M')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = LongMonths(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = LongMonths(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = LongMonths(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = LongMonths(value timesExact scalar)

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): LongMonths {
     return if (scalar == -1) {
       -this
     } else {
       LongMonths(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Long): LongMonths {
     return if (scalar == -1L) {
       -this
     } else {
       LongMonths(value / scalar)
     }
  }

  operator fun rem(scalar: Int) = LongMonths(value % scalar)

  operator fun rem(scalar: Long) = LongMonths(value % scalar)

  operator fun plus(months: IntMonths) = LongMonths(value plusExact months.value)

  operator fun minus(months: IntMonths) = LongMonths(value minusExact months.value)

  operator fun plus(months: LongMonths) = LongMonths(value plusExact months.value)

  operator fun minus(months: LongMonths) = LongMonths(value minusExact months.value)

  operator fun plus(years: IntYears) = this + years.inMonths

  operator fun minus(years: IntYears) = this - years.inMonths

  operator fun plus(years: LongYears) = this + years.inMonths

  operator fun minus(years: LongYears) = this - years.inMonths

  operator fun plus(decades: IntDecades) = this + decades.inMonths

  operator fun minus(decades: IntDecades) = this - decades.inMonths

  operator fun plus(decades: LongDecades) = this + decades.inMonths

  operator fun minus(decades: LongDecades) = this - decades.inMonths

  operator fun plus(centuries: IntCenturies) = this + centuries.inMonths

  operator fun minus(centuries: IntCenturies) = this - centuries.inMonths

  operator fun plus(centuries: LongCenturies) = this + centuries.inMonths

  operator fun minus(centuries: LongCenturies) = this - centuries.inMonths

  inline fun <T> toComponents(action: (years: LongYears, months: IntMonths) -> T): T {
    val years = (value / MONTHS_PER_YEAR).years
    val months = (value % MONTHS_PER_YEAR).toInt().months
    return action(years, months)
  }

  inline fun <T> toComponents(action: (
    decades: LongDecades,
    years: IntYears,
    months: IntMonths
  ) -> T): T {
    val decades = (value / MONTHS_PER_DECADE).decades
    val years = ((value % MONTHS_PER_DECADE) / MONTHS_PER_YEAR).toInt().years
    val months = (value % MONTHS_PER_YEAR).toInt().months
    return action(decades, years, months)
  }

  inline fun <T> toComponents(action: (
    centuries: LongCenturies,
    decades: IntDecades,
    years: IntYears,
    months: IntMonths
  ) -> T): T {
    val centuries = (value / MONTHS_PER_CENTURY).centuries
    val decades = ((value % MONTHS_PER_CENTURY) / MONTHS_PER_DECADE).toInt().decades
    val years = ((value % MONTHS_PER_DECADE) / MONTHS_PER_YEAR).toInt().years
    val months = (value % MONTHS_PER_YEAR).toInt().months
    return action(centuries, decades, years, months)
  }

  /**
   * Convert to [IntMonths].
   * @throws ArithmeticException if overflow occurs
   */
  fun toIntMonths() = IntMonths(value.toIntExact())

  /**
   * Convert to [IntMonths] without checking for overflow.
   */
  @PublishedApi
  internal fun toIntMonthsUnchecked() = IntMonths(value.toInt())

  /**
   * Convert to a unit-less `Int` value.
   * @throws ArithmeticException if overflow occurs
   */
  fun toInt() = value.toIntExact()

  /**
   * Convert to a unit-less `Int` value without checking for overflow.
   */
  internal fun toIntUnchecked() = value.toInt()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: LongMonths = LongMonths(Long.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: LongMonths = LongMonths(Long.MAX_VALUE)
  }
}

/**
 * Convert to [LongMonths].
 */
val Long.months: LongMonths
  get() = LongMonths(this)

/**
 * Multiply by a number of months.
 * @throws ArithmeticException if overflow occurs
 */
operator fun Int.times(months: LongMonths) = months * this

/**
 * Multiply by a number of months.
 * @throws ArithmeticException if overflow occurs
 */
operator fun Long.times(months: LongMonths) = months * this
