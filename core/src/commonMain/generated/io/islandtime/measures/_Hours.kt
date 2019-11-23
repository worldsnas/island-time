//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("HoursKt")

package io.islandtime.measures

import io.islandtime.internal.HOURS_PER_DAY
import io.islandtime.internal.MICROSECONDS_PER_HOUR
import io.islandtime.internal.MILLISECONDS_PER_HOUR
import io.islandtime.internal.MINUTES_PER_HOUR
import io.islandtime.internal.NANOSECONDS_PER_HOUR
import io.islandtime.internal.SECONDS_PER_HOUR
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
 * A number of hours.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntHours(
  /**
   * The underlying value.
   */
  val value: Int
) : Comparable<IntHours> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: IntHours
    get() = if (value < 0) IntHours(value.negateExact()) else this
  /**
   * Convert to nanoseconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inNanoseconds: LongNanoseconds
    get() = (value.toLong() timesExact NANOSECONDS_PER_HOUR).nanoseconds

  /**
   * Convert to nanoseconds without checking for overflow.
   */
  internal val inNanosecondsUnchecked: LongNanoseconds
    get() = (value.toLong() * NANOSECONDS_PER_HOUR).nanoseconds

  /**
   * Convert to microseconds.
   */
  val inMicroseconds: LongMicroseconds
    get() = (value.toLong() * MICROSECONDS_PER_HOUR).microseconds

  /**
   * Convert to milliseconds.
   */
  val inMilliseconds: LongMilliseconds
    get() = (value.toLong() * MILLISECONDS_PER_HOUR).milliseconds

  /**
   * Convert to seconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inSeconds: IntSeconds
    get() = (value timesExact SECONDS_PER_HOUR).seconds

  /**
   * Convert to seconds without checking for overflow.
   */
  internal val inSecondsUnchecked: IntSeconds
    get() = (value * SECONDS_PER_HOUR).seconds

  /**
   * Convert to minutes.
   * @throws ArithmeticException if overflow occurs
   */
  val inMinutes: IntMinutes
    get() = (value timesExact MINUTES_PER_HOUR).minutes

  /**
   * Convert to minutes without checking for overflow.
   */
  internal val inMinutesUnchecked: IntMinutes
    get() = (value * MINUTES_PER_HOUR).minutes

  /**
   * Convert to whole days.
   */
  val inDays: IntDays
    get() = (value / HOURS_PER_DAY).days

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

  override fun compareTo(other: IntHours): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "PT0H"
       value == Int.MIN_VALUE -> "-PT2147483648H"
       else -> buildString {
           if (isNegative()) { append('-') }
           append("PT")
           append(value.absoluteValue)
           append('H')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = IntHours(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = IntHours(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = IntHours(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = this.toLongHours() * scalar

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): IntHours {
     return if (scalar == -1) {
       -this
     } else {
       IntHours(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if the scalar is zero
   */
  operator fun div(scalar: Long): LongHours = this.toLongHours() / scalar
  operator fun rem(scalar: Int) = IntHours(value % scalar)

  operator fun rem(scalar: Long) = this.toLongHours() % scalar

  operator fun plus(nanoseconds: IntNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: IntNanoseconds) = this.inNanoseconds - nanoseconds

  operator fun plus(nanoseconds: LongNanoseconds) = this.toLongHours().inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: LongNanoseconds) = this.toLongHours().inNanoseconds - nanoseconds

  operator fun plus(microseconds: IntMicroseconds) = this.inMicroseconds + microseconds

  operator fun minus(microseconds: IntMicroseconds) = this.inMicroseconds - microseconds

  operator fun plus(microseconds: LongMicroseconds) = this.toLongHours().inMicroseconds +
      microseconds

  operator fun minus(microseconds: LongMicroseconds) = this.toLongHours().inMicroseconds -
      microseconds

  operator fun plus(milliseconds: IntMilliseconds) = this.inMilliseconds + milliseconds

  operator fun minus(milliseconds: IntMilliseconds) = this.inMilliseconds - milliseconds

  operator fun plus(milliseconds: LongMilliseconds) = this.toLongHours().inMilliseconds +
      milliseconds

  operator fun minus(milliseconds: LongMilliseconds) = this.toLongHours().inMilliseconds -
      milliseconds

  operator fun plus(seconds: IntSeconds) = this.inSeconds + seconds

  operator fun minus(seconds: IntSeconds) = this.inSeconds - seconds

  operator fun plus(seconds: LongSeconds) = this.toLongHours().inSeconds + seconds

  operator fun minus(seconds: LongSeconds) = this.toLongHours().inSeconds - seconds

  operator fun plus(minutes: IntMinutes) = this.inMinutes + minutes

  operator fun minus(minutes: IntMinutes) = this.inMinutes - minutes

  operator fun plus(minutes: LongMinutes) = this.toLongHours().inMinutes + minutes

  operator fun minus(minutes: LongMinutes) = this.toLongHours().inMinutes - minutes

  operator fun plus(hours: IntHours) = IntHours(value plusExact hours.value)

  operator fun minus(hours: IntHours) = IntHours(value minusExact hours.value)

  operator fun plus(hours: LongHours) = LongHours(value.toLong() plusExact hours.value)

  operator fun minus(hours: LongHours) = LongHours(value.toLong() minusExact hours.value)

  operator fun plus(days: IntDays) = this + days.inHours

  operator fun minus(days: IntDays) = this - days.inHours

  operator fun plus(days: LongDays) = this.toLongHours() + days.inHours

  operator fun minus(days: LongDays) = this.toLongHours() - days.inHours

  inline fun <T> toComponents(action: (days: IntDays, hours: IntHours) -> T): T {
    val days = this.inDays
    val hours = (this - days)
    return action(days, hours)
  }

  /**
   * Convert to [LongHours].
   */
  fun toLongHours() = LongHours(value.toLong())

  /**
   * Convert to a unit-less `Long` value.
   */
  fun toLong() = value.toLong()

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: IntHours = IntHours(Int.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: IntHours = IntHours(Int.MAX_VALUE)
  }
}

/**
 * Convert to [IntHours].
 */
val Int.hours: IntHours
  get() = IntHours(this)

/**
 * A number of hours.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongHours(
  /**
   * The underlying value.
   */
  val value: Long
) : Comparable<LongHours> {
  /**
   * Get the absolute value.
   * @throws ArithmeticException if overflow occurs
   */
  val absoluteValue: LongHours
    get() = if (value < 0) LongHours(value.negateExact()) else this
  /**
   * Convert to nanoseconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inNanoseconds: LongNanoseconds
    get() = (value timesExact NANOSECONDS_PER_HOUR).nanoseconds

  /**
   * Convert to nanoseconds without checking for overflow.
   */
  internal val inNanosecondsUnchecked: LongNanoseconds
    get() = (value * NANOSECONDS_PER_HOUR).nanoseconds

  /**
   * Convert to microseconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inMicroseconds: LongMicroseconds
    get() = (value timesExact MICROSECONDS_PER_HOUR).microseconds

  /**
   * Convert to microseconds without checking for overflow.
   */
  internal val inMicrosecondsUnchecked: LongMicroseconds
    get() = (value * MICROSECONDS_PER_HOUR).microseconds

  /**
   * Convert to milliseconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inMilliseconds: LongMilliseconds
    get() = (value timesExact MILLISECONDS_PER_HOUR).milliseconds

  /**
   * Convert to milliseconds without checking for overflow.
   */
  internal val inMillisecondsUnchecked: LongMilliseconds
    get() = (value * MILLISECONDS_PER_HOUR).milliseconds

  /**
   * Convert to seconds.
   * @throws ArithmeticException if overflow occurs
   */
  val inSeconds: LongSeconds
    get() = (value timesExact SECONDS_PER_HOUR).seconds

  /**
   * Convert to seconds without checking for overflow.
   */
  internal val inSecondsUnchecked: LongSeconds
    get() = (value * SECONDS_PER_HOUR).seconds

  /**
   * Convert to minutes.
   * @throws ArithmeticException if overflow occurs
   */
  val inMinutes: LongMinutes
    get() = (value timesExact MINUTES_PER_HOUR).minutes

  /**
   * Convert to minutes without checking for overflow.
   */
  internal val inMinutesUnchecked: LongMinutes
    get() = (value * MINUTES_PER_HOUR).minutes

  /**
   * Convert to whole days.
   */
  val inDays: LongDays
    get() = (value / HOURS_PER_DAY).days

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

  override fun compareTo(other: LongHours): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String {
     return when {
       isZero() -> "PT0H"
       value == Long.MIN_VALUE -> "-PT9223372036854775808H"
       else -> buildString {
           if (isNegative()) { append('-') }
           append("PT")
           append(value.absoluteValue)
           append('H')
       }
     }
  }

  /**
   * Negate the value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun unaryMinus() = LongHours(value.negateExact())

  /**
   * Negate the value without checking for overflow.
   */
  internal fun negateUnchecked() = LongHours(-value)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Int) = LongHours(value timesExact scalar)

  /**
   * Multiply by a scalar value.
   * @throws ArithmeticException if overflow occurs
   */
  operator fun times(scalar: Long) = LongHours(value timesExact scalar)

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Int): LongHours {
     return if (scalar == -1) {
       -this
     } else {
       LongHours(value / scalar)
     }
  }

  /**
   * Divide by a scalar value.
   * @throws ArithmeticException if overflow occurs or the scalar is zero
   */
  operator fun div(scalar: Long): LongHours {
     return if (scalar == -1L) {
       -this
     } else {
       LongHours(value / scalar)
     }
  }

  operator fun rem(scalar: Int) = LongHours(value % scalar)

  operator fun rem(scalar: Long) = LongHours(value % scalar)

  operator fun plus(nanoseconds: IntNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: IntNanoseconds) = this.inNanoseconds - nanoseconds

  operator fun plus(nanoseconds: LongNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: LongNanoseconds) = this.inNanoseconds - nanoseconds

  operator fun plus(microseconds: IntMicroseconds) = this.inMicroseconds + microseconds

  operator fun minus(microseconds: IntMicroseconds) = this.inMicroseconds - microseconds

  operator fun plus(microseconds: LongMicroseconds) = this.inMicroseconds + microseconds

  operator fun minus(microseconds: LongMicroseconds) = this.inMicroseconds - microseconds

  operator fun plus(milliseconds: IntMilliseconds) = this.inMilliseconds + milliseconds

  operator fun minus(milliseconds: IntMilliseconds) = this.inMilliseconds - milliseconds

  operator fun plus(milliseconds: LongMilliseconds) = this.inMilliseconds + milliseconds

  operator fun minus(milliseconds: LongMilliseconds) = this.inMilliseconds - milliseconds

  operator fun plus(seconds: IntSeconds) = this.inSeconds + seconds

  operator fun minus(seconds: IntSeconds) = this.inSeconds - seconds

  operator fun plus(seconds: LongSeconds) = this.inSeconds + seconds

  operator fun minus(seconds: LongSeconds) = this.inSeconds - seconds

  operator fun plus(minutes: IntMinutes) = this.inMinutes + minutes

  operator fun minus(minutes: IntMinutes) = this.inMinutes - minutes

  operator fun plus(minutes: LongMinutes) = this.inMinutes + minutes

  operator fun minus(minutes: LongMinutes) = this.inMinutes - minutes

  operator fun plus(hours: IntHours) = LongHours(value plusExact hours.value)

  operator fun minus(hours: IntHours) = LongHours(value minusExact hours.value)

  operator fun plus(hours: LongHours) = LongHours(value plusExact hours.value)

  operator fun minus(hours: LongHours) = LongHours(value minusExact hours.value)

  operator fun plus(days: IntDays) = this + days.inHours

  operator fun minus(days: IntDays) = this - days.inHours

  operator fun plus(days: LongDays) = this + days.inHours

  operator fun minus(days: LongDays) = this - days.inHours

  inline fun <T> toComponents(action: (days: LongDays, hours: IntHours) -> T): T {
    val days = this.inDays
    val hours = (this - days).toIntHoursUnchecked()
    return action(days, hours)
  }

  /**
   * Convert to [IntHours].
   * @throws ArithmeticException if overflow occurs
   */
  fun toIntHours() = IntHours(value.toIntExact())

  /**
   * Convert to [IntHours] without checking for overflow.
   */
  @PublishedApi
  internal fun toIntHoursUnchecked() = IntHours(value.toInt())

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
    val MIN: LongHours = LongHours(Long.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: LongHours = LongHours(Long.MAX_VALUE)
  }
}

/**
 * Convert to [LongHours].
 */
val Long.hours: LongHours
  get() = LongHours(this)
