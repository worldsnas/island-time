//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("YearsKt")

package io.islandtime.measures

import io.islandtime.internal.MONTHS_PER_YEAR
import io.islandtime.internal.YEARS_PER_CENTURY
import io.islandtime.internal.YEARS_PER_DECADE
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
import kotlin.Suppress
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

/**
 * A number of years.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntYears(
  /**
   * The underlying value.
   */
  val value: Int
) : Comparable<IntYears> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: IntYears
    get() = if (value < 0) IntYears(value.negateExact()) else this
  /**
   * Convert to months.
   * @throws ArithmeticException if overflow occurs
   */
  val inMonths: IntMonths
    get() = (value timesExact MONTHS_PER_YEAR).months

  /**
   * Convert to months without checking for overflow.
   */
  internal val inMonthsUnchecked: IntMonths
    get() = (value * MONTHS_PER_YEAR).months

  /**
   * Convert to whole decades.
   */
  val inDecades: IntDecades
    get() = (value / YEARS_PER_DECADE).decades

  /**
   * Convert to whole centuries.
   */
  val inCenturies: IntCenturies
    get() = (value / YEARS_PER_CENTURY).centuries

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

  override fun compareTo(other: IntYears): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "P0Y"
       value == Int.MIN_VALUE -> "-P2147483648Y"
       else -> buildString {
           if (isNegative()) { append('-') }
           append("P")
           append(value.absoluteValue)
           append('Y')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = IntYears(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = IntYears(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = IntYears(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = this.toLongYears() * scalar

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): IntYears {
     return if (scalar == -1) {
       -this
     } else {
       IntYears(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if the scalar is zero
   */
  operator fun div(scalar: Long): LongYears = this.toLongYears() / scalar
  operator fun rem(scalar: Int) = IntYears(value % scalar)

  operator fun rem(scalar: Long) = this.toLongYears() % scalar

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun minus(months: IntMonths) = this.inMonths - months

  operator fun plus(months: LongMonths) = this.toLongYears().inMonths + months

  operator fun minus(months: LongMonths) = this.toLongYears().inMonths - months

  operator fun plus(years: IntYears) = IntYears(value plusExact years.value)

  operator fun minus(years: IntYears) = IntYears(value minusExact years.value)

  operator fun plus(years: LongYears) = LongYears(value.toLong() plusExact years.value)

  operator fun minus(years: LongYears) = LongYears(value.toLong() minusExact years.value)

  operator fun plus(decades: IntDecades) = this + decades.inYears

  operator fun minus(decades: IntDecades) = this - decades.inYears

  operator fun plus(decades: LongDecades) = this.toLongYears() + decades.inYears

  operator fun minus(decades: LongDecades) = this.toLongYears() - decades.inYears

  operator fun plus(centuries: IntCenturies) = this + centuries.inYears

  operator fun minus(centuries: IntCenturies) = this - centuries.inYears

  operator fun plus(centuries: LongCenturies) = this.toLongYears() + centuries.inYears

  operator fun minus(centuries: LongCenturies) = this.toLongYears() - centuries.inYears

  inline fun <T> toComponents(action: (decades: IntDecades, years: IntYears) -> T): T {
    val decades = this.inDecades
    val years = (this - decades)
    return action(decades, years)
  }

  inline fun <T> toComponents(action: (
    centuries: IntCenturies,
    decades: IntDecades,
    years: IntYears
  ) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries).inDecades
    val years = (this - centuries - decades)
    return action(centuries, decades, years)
  }

  /**
   * Convert to [LongYears].
   */
  fun toLongYears() = LongYears(value.toLong())

  /**
   * Convert to a unit-less `Long` value.
   */
  fun toLong() = value.toLong()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: IntYears = IntYears(Int.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: IntYears = IntYears(Int.MAX_VALUE)
  }
}

/**
 * Convert to [IntYears].
 */
val Int.years: IntYears
  get() = IntYears(this)

/**
 * A number of years.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongYears(
  /**
   * The underlying value.
   */
  val value: Long
) : Comparable<LongYears> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: LongYears
    get() = if (value < 0) LongYears(value.negateExact()) else this
  /**
   * Convert to months.
   * @throws ArithmeticException if overflow occurs
   */
  val inMonths: LongMonths
    get() = (value timesExact MONTHS_PER_YEAR).months

  /**
   * Convert to months without checking for overflow.
   */
  internal val inMonthsUnchecked: LongMonths
    get() = (value * MONTHS_PER_YEAR).months

  /**
   * Convert to whole decades.
   */
  val inDecades: LongDecades
    get() = (value / YEARS_PER_DECADE).decades

  /**
   * Convert to whole centuries.
   */
  val inCenturies: LongCenturies
    get() = (value / YEARS_PER_CENTURY).centuries

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

  override fun compareTo(other: LongYears): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "P0Y"
       value == Long.MIN_VALUE -> "-P9223372036854775808Y"
       else -> buildString {
           if (isNegative()) { append('-') }
           append("P")
           append(value.absoluteValue)
           append('Y')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = LongYears(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = LongYears(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = LongYears(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = LongYears(value timesExact scalar)

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): LongYears {
     return if (scalar == -1) {
       -this
     } else {
       LongYears(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Long): LongYears {
     return if (scalar == -1L) {
       -this
     } else {
       LongYears(value / scalar)
     }
  }

  operator fun rem(scalar: Int) = LongYears(value % scalar)

  operator fun rem(scalar: Long) = LongYears(value % scalar)

  operator fun plus(months: IntMonths) = this.inMonths + months

  operator fun minus(months: IntMonths) = this.inMonths - months

  operator fun plus(months: LongMonths) = this.inMonths + months

  operator fun minus(months: LongMonths) = this.inMonths - months

  operator fun plus(years: IntYears) = LongYears(value plusExact years.value)

  operator fun minus(years: IntYears) = LongYears(value minusExact years.value)

  operator fun plus(years: LongYears) = LongYears(value plusExact years.value)

  operator fun minus(years: LongYears) = LongYears(value minusExact years.value)

  operator fun plus(decades: IntDecades) = this + decades.inYears

  operator fun minus(decades: IntDecades) = this - decades.inYears

  operator fun plus(decades: LongDecades) = this + decades.inYears

  operator fun minus(decades: LongDecades) = this - decades.inYears

  operator fun plus(centuries: IntCenturies) = this + centuries.inYears

  operator fun minus(centuries: IntCenturies) = this - centuries.inYears

  operator fun plus(centuries: LongCenturies) = this + centuries.inYears

  operator fun minus(centuries: LongCenturies) = this - centuries.inYears

  inline fun <T> toComponents(action: (decades: LongDecades, years: IntYears) -> T): T {
    val decades = this.inDecades
    val years = (this - decades).toIntYearsUnchecked()
    return action(decades, years)
  }

  inline fun <T> toComponents(action: (
    centuries: LongCenturies,
    decades: IntDecades,
    years: IntYears
  ) -> T): T {
    val centuries = this.inCenturies
    val decades = (this - centuries).toIntYearsUnchecked().inDecades
    val years = (this - centuries - decades).toIntYearsUnchecked()
    return action(centuries, decades, years)
  }

  /**
   * Convert to [IntYears].
   * @throws ArithmeticException if overflow occurs
   */
  fun toIntYears() = IntYears(value.toIntExact())

  /**
   * Convert to [IntYears] without checking for overflow.
   */
  @PublishedApi
  internal fun toIntYearsUnchecked() = IntYears(value.toInt())

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
    val MIN: LongYears = LongYears(Long.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: LongYears = LongYears(Long.MAX_VALUE)
  }
}

/**
 * Convert to [LongYears].
 */
val Long.years: LongYears
  get() = LongYears(this)
