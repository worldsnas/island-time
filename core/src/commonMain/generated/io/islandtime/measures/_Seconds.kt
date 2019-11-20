//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("SecondsKt")

package io.islandtime.measures

import io.islandtime.internal.MICROSECONDS_PER_SECOND
import io.islandtime.internal.MILLISECONDS_PER_SECOND
import io.islandtime.internal.NANOSECONDS_PER_SECOND
import io.islandtime.internal.SECONDS_PER_DAY
import io.islandtime.internal.SECONDS_PER_HOUR
import io.islandtime.internal.SECONDS_PER_MINUTE
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

/**
 * A number of seconds.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class IntSeconds(
  /**
   * The underlying value.
   */
  val value: Int
) : Comparable<IntSeconds> {
  /**
   * Get the absolute value.
   */
  val absoluteValue: IntSeconds
    get() = IntSeconds(value.absoluteValue)
  /**
   * Convert to nanoseconds.
   */
  val inNanoseconds: LongNanoseconds
    get() = (value.toLong() * NANOSECONDS_PER_SECOND).nanoseconds

  /**
   * Convert to microseconds.
   */
  val inMicroseconds: LongMicroseconds
    get() = (value.toLong() * MICROSECONDS_PER_SECOND).microseconds

  /**
   * Convert to milliseconds.
   */
  val inMilliseconds: LongMilliseconds
    get() = (value.toLong() * MILLISECONDS_PER_SECOND).milliseconds

  /**
   * Convert to whole minutes.
   */
  val inMinutes: IntMinutes
    get() = (value / SECONDS_PER_MINUTE).minutes

  /**
   * Convert to whole hours.
   */
  val inHours: IntHours
    get() = (value / SECONDS_PER_HOUR).hours

  /**
   * Convert to whole days.
   */
  val inDays: IntDays
    get() = (value / SECONDS_PER_DAY).days

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

  override fun compareTo(other: IntSeconds): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String = if (isZero()) {
      "PT0S"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("PT")
          append(value.absoluteValue)
          append('S')
      }
  }
  operator fun unaryMinus() = IntSeconds(-value)

  operator fun times(scalar: Int) = IntSeconds(value * scalar)

  operator fun times(scalar: Long) = this.toLong() * scalar

  operator fun div(scalar: Int) = IntSeconds(value / scalar)

  operator fun div(scalar: Long) = this.toLong() / scalar

  operator fun rem(scalar: Int) = IntSeconds(value % scalar)

  operator fun rem(scalar: Long) = this.toLong() % scalar

  operator fun plus(nanoseconds: IntNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: IntNanoseconds) = this.inNanoseconds - nanoseconds

  operator fun plus(nanoseconds: LongNanoseconds) = this.toLong().inNanoseconds + nanoseconds

  operator fun minus(nanoseconds: LongNanoseconds) = this.toLong().inNanoseconds - nanoseconds

  operator fun plus(microseconds: IntMicroseconds) = this.inMicroseconds + microseconds

  operator fun minus(microseconds: IntMicroseconds) = this.inMicroseconds - microseconds

  operator fun plus(microseconds: LongMicroseconds) = this.toLong().inMicroseconds + microseconds

  operator fun minus(microseconds: LongMicroseconds) = this.toLong().inMicroseconds - microseconds

  operator fun plus(milliseconds: IntMilliseconds) = this.inMilliseconds + milliseconds

  operator fun minus(milliseconds: IntMilliseconds) = this.inMilliseconds - milliseconds

  operator fun plus(milliseconds: LongMilliseconds) = this.toLong().inMilliseconds + milliseconds

  operator fun minus(milliseconds: LongMilliseconds) = this.toLong().inMilliseconds - milliseconds

  operator fun plus(seconds: IntSeconds) = IntSeconds(value + seconds.value)

  operator fun minus(seconds: IntSeconds) = IntSeconds(value - seconds.value)

  operator fun plus(seconds: LongSeconds) = LongSeconds(value.toLong() + seconds.value)

  operator fun minus(seconds: LongSeconds) = LongSeconds(value.toLong() - seconds.value)

  operator fun plus(minutes: IntMinutes) = this + minutes.inSeconds

  operator fun minus(minutes: IntMinutes) = this - minutes.inSeconds

  operator fun plus(minutes: LongMinutes) = this.toLong() + minutes.inSeconds

  operator fun minus(minutes: LongMinutes) = this.toLong() - minutes.inSeconds

  operator fun plus(hours: IntHours) = this + hours.inSeconds

  operator fun minus(hours: IntHours) = this - hours.inSeconds

  operator fun plus(hours: LongHours) = this.toLong() + hours.inSeconds

  operator fun minus(hours: LongHours) = this.toLong() - hours.inSeconds

  operator fun plus(days: IntDays) = this + days.inSeconds

  operator fun minus(days: IntDays) = this - days.inSeconds

  operator fun plus(days: LongDays) = this.toLong() + days.inSeconds

  operator fun minus(days: LongDays) = this.toLong() - days.inSeconds

  inline fun <T> toComponents(action: (minutes: IntMinutes, seconds: IntSeconds) -> T): T {
    val minutes = this.inMinutes
    val seconds = (this - minutes)
    return action(minutes, seconds)
  }

  inline fun <T> toComponents(action: (
    hours: IntHours,
    minutes: IntMinutes,
    seconds: IntSeconds
  ) -> T): T {
    val hours = this.inHours
    val minutes = (this - hours).inMinutes
    val seconds = (this - hours - minutes)
    return action(hours, minutes, seconds)
  }

  inline fun <T> toComponents(action: (
    days: IntDays,
    hours: IntHours,
    minutes: IntMinutes,
    seconds: IntSeconds
  ) -> T): T {
    val days = this.inDays
    val hours = (this - days).inHours
    val minutes = (this - days - hours).inMinutes
    val seconds = (this - days - hours - minutes)
    return action(days, hours, minutes, seconds)
  }

  fun toLong() = LongSeconds(value.toLong())

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: IntSeconds = IntSeconds(Int.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: IntSeconds = IntSeconds(Int.MAX_VALUE)
  }
}

/**
 * Convert to [IntSeconds].
 */
val Int.seconds: IntSeconds
  get() = IntSeconds(this)

/**
 * A number of seconds.
 */
@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongSeconds(
  /**
   * The underlying value.
   */
  val value: Long
) : Comparable<LongSeconds> {
  /**
   * Get the absolute value.
   */
  val absoluteValue: LongSeconds
    get() = LongSeconds(value.absoluteValue)
  /**
   * Convert to nanoseconds.
   */
  val inNanoseconds: LongNanoseconds
    get() = (value * NANOSECONDS_PER_SECOND).nanoseconds

  /**
   * Convert to microseconds.
   */
  val inMicroseconds: LongMicroseconds
    get() = (value * MICROSECONDS_PER_SECOND).microseconds

  /**
   * Convert to milliseconds.
   */
  val inMilliseconds: LongMilliseconds
    get() = (value * MILLISECONDS_PER_SECOND).milliseconds

  /**
   * Convert to whole minutes.
   */
  val inMinutes: LongMinutes
    get() = (value / SECONDS_PER_MINUTE).minutes

  /**
   * Convert to whole hours.
   */
  val inHours: LongHours
    get() = (value / SECONDS_PER_HOUR).hours

  /**
   * Convert to whole days.
   */
  val inDays: LongDays
    get() = (value / SECONDS_PER_DAY).days

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

  override fun compareTo(other: LongSeconds): Int = value.compareTo(other.value)

  /**
   * Convert to an ISO-8601 time interval representation.
   */
  override fun toString(): String = if (isZero()) {
      "PT0S"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("PT")
          append(value.absoluteValue)
          append('S')
      }
  }
  operator fun unaryMinus() = LongSeconds(-value)

  operator fun times(scalar: Int) = LongSeconds(value * scalar)

  operator fun times(scalar: Long) = LongSeconds(value * scalar)

  operator fun div(scalar: Int) = LongSeconds(value / scalar)

  operator fun div(scalar: Long) = LongSeconds(value / scalar)

  operator fun rem(scalar: Int) = LongSeconds(value % scalar)

  operator fun rem(scalar: Long) = LongSeconds(value % scalar)

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

  operator fun plus(seconds: IntSeconds) = LongSeconds(value + seconds.value)

  operator fun minus(seconds: IntSeconds) = LongSeconds(value - seconds.value)

  operator fun plus(seconds: LongSeconds) = LongSeconds(value + seconds.value)

  operator fun minus(seconds: LongSeconds) = LongSeconds(value - seconds.value)

  operator fun plus(minutes: IntMinutes) = this + minutes.inSeconds

  operator fun minus(minutes: IntMinutes) = this - minutes.inSeconds

  operator fun plus(minutes: LongMinutes) = this + minutes.inSeconds

  operator fun minus(minutes: LongMinutes) = this - minutes.inSeconds

  operator fun plus(hours: IntHours) = this + hours.inSeconds

  operator fun minus(hours: IntHours) = this - hours.inSeconds

  operator fun plus(hours: LongHours) = this + hours.inSeconds

  operator fun minus(hours: LongHours) = this - hours.inSeconds

  operator fun plus(days: IntDays) = this + days.inSeconds

  operator fun minus(days: IntDays) = this - days.inSeconds

  operator fun plus(days: LongDays) = this + days.inSeconds

  operator fun minus(days: LongDays) = this - days.inSeconds

  /**
   * Convert to nanoseconds.
   */
  fun inNanosecondsExact() = (value timesExact NANOSECONDS_PER_SECOND).nanoseconds

  /**
   * Convert to microseconds.
   */
  fun inMicrosecondsExact() = (value timesExact MICROSECONDS_PER_SECOND).microseconds

  /**
   * Convert to milliseconds.
   */
  fun inMillisecondsExact() = (value timesExact MILLISECONDS_PER_SECOND).milliseconds

  inline fun <T> toComponents(action: (minutes: LongMinutes, seconds: IntSeconds) -> T): T {
    val minutes = this.inMinutes
    val seconds = (this - minutes).toInt()
    return action(minutes, seconds)
  }

  inline fun <T> toComponents(action: (
    hours: LongHours,
    minutes: IntMinutes,
    seconds: IntSeconds
  ) -> T): T {
    val hours = this.inHours
    val minutes = (this - hours).toInt().inMinutes
    val seconds = (this - hours - minutes).toInt()
    return action(hours, minutes, seconds)
  }

  inline fun <T> toComponents(action: (
    days: LongDays,
    hours: IntHours,
    minutes: IntMinutes,
    seconds: IntSeconds
  ) -> T): T {
    val days = this.inDays
    val hours = (this - days).toInt().inHours
    val minutes = (this - days - hours).toInt().inMinutes
    val seconds = (this - days - hours - minutes).toInt()
    return action(days, hours, minutes, seconds)
  }

  fun toInt() = IntSeconds(value.toInt())

  fun toIntExact() = IntSeconds(value.toIntExact())

  companion object {
    /**
     * The smallest supported value.
     */
    val MIN: LongSeconds = LongSeconds(Long.MIN_VALUE)

    /**
     * The largest supported value.
     */
    val MAX: LongSeconds = LongSeconds(Long.MAX_VALUE)
  }
}

/**
 * Convert to [LongSeconds].
 */
val Long.seconds: LongSeconds
  get() = LongSeconds(this)
