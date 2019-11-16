//
// This file is auto-generated by 'tools:code-generator'
//
@file:JvmMultifileClass
@file:JvmName("DaysKt")

package io.islandtime.measures

import io.islandtime.internal.DAYS_PER_WEEK
import io.islandtime.internal.HOURS_PER_DAY
import io.islandtime.internal.MICROSECONDS_PER_DAY
import io.islandtime.internal.MILLISECONDS_PER_DAY
import io.islandtime.internal.MINUTES_PER_DAY
import io.islandtime.internal.NANOSECONDS_PER_DAY
import io.islandtime.internal.SECONDS_PER_DAY
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
inline class IntDays(
  val value: Int
) : Comparable<IntDays> {
  val absoluteValue: IntDays
    get() = IntDays(value.absoluteValue)
  val inNanoseconds: LongNanoseconds
    get() = (this.value.toLong() * NANOSECONDS_PER_DAY).nanoseconds

  val inMicroseconds: LongMicroseconds
    get() = (this.value.toLong() * MICROSECONDS_PER_DAY).microseconds

  val inMilliseconds: LongMilliseconds
    get() = (this.value.toLong() * MILLISECONDS_PER_DAY).milliseconds

  val inSeconds: IntSeconds
    get() = (this.value * SECONDS_PER_DAY).seconds

  val inMinutes: IntMinutes
    get() = (this.value * MINUTES_PER_DAY).minutes

  val inHours: IntHours
    get() = (this.value * HOURS_PER_DAY).hours

  val inWeeks: IntWeeks
    get() = (this.value / DAYS_PER_WEEK).weeks

  fun isZero(): Boolean = value == 0

  fun isNegative(): Boolean = value < 0

  fun isPositive(): Boolean = value > 0

  override fun compareTo(other: IntDays): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0D"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('D')
      }
  }
  fun inNanosecondsExact() = (this.value.toLong() timesExact NANOSECONDS_PER_DAY).nanoseconds

  fun inMicrosecondsExact() = (this.value.toLong() timesExact MICROSECONDS_PER_DAY).microseconds

  fun inSecondsExact() = (this.value timesExact SECONDS_PER_DAY).seconds

  fun inMinutesExact() = (this.value timesExact MINUTES_PER_DAY).minutes

  fun inHoursExact() = (this.value timesExact HOURS_PER_DAY).hours

  operator fun unaryMinus() = IntDays(-value)

  operator fun plus(nanoseconds: IntNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun plus(nanoseconds: LongNanoseconds) = this.toLong().inNanoseconds + nanoseconds

  operator fun plus(microseconds: IntMicroseconds) = this.inMicroseconds + microseconds

  operator fun plus(microseconds: LongMicroseconds) = this.toLong().inMicroseconds + microseconds

  operator fun plus(milliseconds: IntMilliseconds) = this.inMilliseconds + milliseconds

  operator fun plus(milliseconds: LongMilliseconds) = this.toLong().inMilliseconds + milliseconds

  operator fun plus(seconds: IntSeconds) = this.inSeconds + seconds

  operator fun plus(seconds: LongSeconds) = this.toLong().inSeconds + seconds

  operator fun plus(minutes: IntMinutes) = this.inMinutes + minutes

  operator fun plus(minutes: LongMinutes) = this.toLong().inMinutes + minutes

  operator fun plus(hours: IntHours) = this.inHours + hours

  operator fun plus(hours: LongHours) = this.toLong().inHours + hours

  operator fun plus(days: IntDays) = IntDays(this.value + days.value)

  operator fun plus(days: LongDays) = LongDays(this.value.toLong() + days.value)

  operator fun plus(weeks: IntWeeks) = this + weeks.inDays

  operator fun plus(weeks: LongWeeks) = this.toLong() + weeks.inDays

  operator fun minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

  operator fun minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

  operator fun minus(microseconds: IntMicroseconds) = plus(-microseconds)

  operator fun minus(microseconds: LongMicroseconds) = plus(-microseconds)

  operator fun minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

  operator fun minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

  operator fun minus(seconds: IntSeconds) = plus(-seconds)

  operator fun minus(seconds: LongSeconds) = plus(-seconds)

  operator fun minus(minutes: IntMinutes) = plus(-minutes)

  operator fun minus(minutes: LongMinutes) = plus(-minutes)

  operator fun minus(hours: IntHours) = plus(-hours)

  operator fun minus(hours: LongHours) = plus(-hours)

  operator fun minus(days: IntDays) = plus(-days)

  operator fun minus(days: LongDays) = plus(-days)

  operator fun minus(weeks: IntWeeks) = plus(-weeks)

  operator fun minus(weeks: LongWeeks) = plus(-weeks)

  operator fun times(scalar: Int) = IntDays(this.value * scalar)

  operator fun times(scalar: Long) = this.toLong() * scalar

  operator fun div(scalar: Int) = IntDays(this.value / scalar)

  operator fun div(scalar: Long) = this.toLong() / scalar

  operator fun rem(scalar: Int) = IntDays(this.value % scalar)

  operator fun rem(scalar: Long) = this.toLong() % scalar

  inline fun <T> toComponents(action: (weeks: IntWeeks, days: IntDays) -> T): T {
    val weeks = this.inWeeks
    val days = (this - weeks)
    return action(weeks, days)
  }

  fun toLong() = LongDays(this.value.toLong())

  companion object {
    val MIN: IntDays = IntDays(Int.MIN_VALUE)

    val MAX: IntDays = IntDays(Int.MAX_VALUE)
  }
}

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class LongDays(
  val value: Long
) : Comparable<LongDays> {
  val absoluteValue: LongDays
    get() = LongDays(value.absoluteValue)
  val inNanoseconds: LongNanoseconds
    get() = (this.value * NANOSECONDS_PER_DAY).nanoseconds

  val inMicroseconds: LongMicroseconds
    get() = (this.value * MICROSECONDS_PER_DAY).microseconds

  val inMilliseconds: LongMilliseconds
    get() = (this.value * MILLISECONDS_PER_DAY).milliseconds

  val inSeconds: LongSeconds
    get() = (this.value * SECONDS_PER_DAY).seconds

  val inMinutes: LongMinutes
    get() = (this.value * MINUTES_PER_DAY).minutes

  val inHours: LongHours
    get() = (this.value * HOURS_PER_DAY).hours

  val inWeeks: LongWeeks
    get() = (this.value / DAYS_PER_WEEK).weeks

  fun isZero(): Boolean = value == 0L

  fun isNegative(): Boolean = value < 0L

  fun isPositive(): Boolean = value > 0L

  override fun compareTo(other: LongDays): Int = value.compareTo(other.value)

  override fun toString(): String = if (isZero()) {
      "P0D"
  } else {
      buildString {
          if (isNegative()) { append('-') }
          append("P")
          append(value.absoluteValue)
          append('D')
      }
  }
  fun inNanosecondsExact() = (this.value timesExact NANOSECONDS_PER_DAY).nanoseconds

  fun inMicrosecondsExact() = (this.value timesExact MICROSECONDS_PER_DAY).microseconds

  fun inMillisecondsExact() = (this.value timesExact MILLISECONDS_PER_DAY).milliseconds

  fun inSecondsExact() = (this.value timesExact SECONDS_PER_DAY).seconds

  fun inMinutesExact() = (this.value timesExact MINUTES_PER_DAY).minutes

  fun inHoursExact() = (this.value timesExact HOURS_PER_DAY).hours

  operator fun unaryMinus() = LongDays(-value)

  operator fun plus(nanoseconds: IntNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun plus(nanoseconds: LongNanoseconds) = this.inNanoseconds + nanoseconds

  operator fun plus(microseconds: IntMicroseconds) = this.inMicroseconds + microseconds

  operator fun plus(microseconds: LongMicroseconds) = this.inMicroseconds + microseconds

  operator fun plus(milliseconds: IntMilliseconds) = this.inMilliseconds + milliseconds

  operator fun plus(milliseconds: LongMilliseconds) = this.inMilliseconds + milliseconds

  operator fun plus(seconds: IntSeconds) = this.inSeconds + seconds

  operator fun plus(seconds: LongSeconds) = this.inSeconds + seconds

  operator fun plus(minutes: IntMinutes) = this.inMinutes + minutes

  operator fun plus(minutes: LongMinutes) = this.inMinutes + minutes

  operator fun plus(hours: IntHours) = this.inHours + hours

  operator fun plus(hours: LongHours) = this.inHours + hours

  operator fun plus(days: IntDays) = LongDays(this.value + days.value)

  operator fun plus(days: LongDays) = LongDays(this.value + days.value)

  operator fun plus(weeks: IntWeeks) = this + weeks.inDays

  operator fun plus(weeks: LongWeeks) = this + weeks.inDays

  operator fun minus(nanoseconds: IntNanoseconds) = plus(-nanoseconds)

  operator fun minus(nanoseconds: LongNanoseconds) = plus(-nanoseconds)

  operator fun minus(microseconds: IntMicroseconds) = plus(-microseconds)

  operator fun minus(microseconds: LongMicroseconds) = plus(-microseconds)

  operator fun minus(milliseconds: IntMilliseconds) = plus(-milliseconds)

  operator fun minus(milliseconds: LongMilliseconds) = plus(-milliseconds)

  operator fun minus(seconds: IntSeconds) = plus(-seconds)

  operator fun minus(seconds: LongSeconds) = plus(-seconds)

  operator fun minus(minutes: IntMinutes) = plus(-minutes)

  operator fun minus(minutes: LongMinutes) = plus(-minutes)

  operator fun minus(hours: IntHours) = plus(-hours)

  operator fun minus(hours: LongHours) = plus(-hours)

  operator fun minus(days: IntDays) = plus(-days)

  operator fun minus(days: LongDays) = plus(-days)

  operator fun minus(weeks: IntWeeks) = plus(-weeks)

  operator fun minus(weeks: LongWeeks) = plus(-weeks)

  operator fun times(scalar: Int) = LongDays(this.value * scalar)

  operator fun times(scalar: Long) = LongDays(this.value * scalar)

  operator fun div(scalar: Int) = LongDays(this.value / scalar)

  operator fun div(scalar: Long) = LongDays(this.value / scalar)

  operator fun rem(scalar: Int) = LongDays(this.value % scalar)

  operator fun rem(scalar: Long) = LongDays(this.value % scalar)

  inline fun <T> toComponents(action: (weeks: LongWeeks, days: IntDays) -> T): T {
    val weeks = this.inWeeks
    val days = (this - weeks).toInt()
    return action(weeks, days)
  }

  fun toInt() = IntDays(this.value.toInt())

  fun toIntExact() = IntDays(this.value.toIntExact())

  companion object {
    val MIN: LongDays = LongDays(Long.MIN_VALUE)

    val MAX: LongDays = LongDays(Long.MAX_VALUE)
  }
}

val Int.days: IntDays
  get() = IntDays(this)

val Long.days: LongDays
  get() = LongDays(this)
