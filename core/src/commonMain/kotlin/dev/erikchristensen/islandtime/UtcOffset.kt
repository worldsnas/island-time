package dev.erikchristensen.islandtime

import dev.erikchristensen.islandtime.internal.SECONDS_PER_HOUR
import dev.erikchristensen.islandtime.internal.SECONDS_PER_MINUTE
import dev.erikchristensen.islandtime.internal.appendZeroPadded
import dev.erikchristensen.islandtime.interval.*
import dev.erikchristensen.islandtime.parser.*

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
inline class UtcOffset internal constructor(
    val totalSeconds: IntSeconds
) : Comparable<UtcOffset> {

    val isValid: Boolean get() = totalSeconds in MIN_TOTAL_SECONDS..MAX_TOTAL_SECONDS

    /**
     * Is this the UTC offset of +00:00?
     */
    inline val isZero get() = this == ZERO

    /**
     * Break a UTC offset down into components.  The sign will indicate whether the offset is positive or negative while
     * each component will be positive.
     */
    fun <T> toComponents(
        action: (sign: Int, hours: IntHours, minutes: IntMinutes, seconds: IntSeconds) -> T
    ): T {
        val sign = if (totalSeconds.isNegative) -1 else 1
        val absTotalSeconds = totalSeconds.absoluteValue
        val hours = (absTotalSeconds.value / SECONDS_PER_HOUR).hours
        val minutes = ((absTotalSeconds.value % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE).minutes
        val seconds = absTotalSeconds % SECONDS_PER_MINUTE

        return action(sign, hours, minutes, seconds)
    }

    /**
     * Break a UTC offset down into components.  If the offset is negative, each component will be negative.
     */
    fun <T> toComponents(
        action: (hours: IntHours, minutes: IntMinutes, seconds: IntSeconds) -> T
    ): T {
        val hours = (totalSeconds.value / SECONDS_PER_HOUR).hours
        val minutes = ((totalSeconds.value % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE).minutes
        val seconds = totalSeconds % SECONDS_PER_MINUTE

        return action(hours, minutes, seconds)
    }


    override fun compareTo(other: UtcOffset) = totalSeconds.compareTo(other.totalSeconds)

    override fun toString(): String {
        return if (isZero) {
            "Z"
        } else {
            buildString(MAX_UTC_OFFSET_STRING_LENGTH) { appendUtcOffset(this@UtcOffset) }
        }
    }

    companion object {
        val MAX_TOTAL_SECONDS = (18 * SECONDS_PER_HOUR).seconds
        val MIN_TOTAL_SECONDS = (-18 * SECONDS_PER_HOUR).seconds

        val MIN = UtcOffset(MAX_TOTAL_SECONDS)
        val MAX = UtcOffset(MIN_TOTAL_SECONDS)
        val ZERO = UtcOffset(0.seconds)

        /**
         * Create a UTC time offset from the total number of seconds to offset by
         */
        operator fun invoke(totalSeconds: IntSeconds): UtcOffset {
            if (totalSeconds !in MIN_TOTAL_SECONDS..MAX_TOTAL_SECONDS) {
                throw DateTimeException("'$totalSeconds' is outside the valid offset range of +/-18:00")
            }

            return UtcOffset(totalSeconds)
        }

        /**
         * Create a UTC time offset of hours, minutes, and seconds. Each component must be within its valid range and
         * without any mixed positive and negative values.
         * @param hours hours to offset by, within +/-18
         * @param minutes minutes to offset by, within +/-59
         * @param seconds seconds to offset by, within +/-59
         * @return a [UtcOffset]
         */
        operator fun invoke(
            hours: IntHours,
            minutes: IntMinutes = 0.minutes,
            seconds: IntSeconds = 0.seconds
        ): UtcOffset {
            validateUtcOffsetComponents(hours, minutes, seconds)
            return invoke(hours + minutes + seconds)
        }
    }
}

/**
 * Convert a duration of hours into a UTC time offset of the same length
 */
fun IntHours.asUtcOffset() = UtcOffset(this.asSeconds())

/**
 * Convert a duration of minutes into a UTC time offset of the same length
 */
fun IntMinutes.asUtcOffset() = UtcOffset(this.asSeconds())

/**
 * Convert a duration of seconds into a UTC time offset of the same length
 */
fun IntSeconds.asUtcOffset() = UtcOffset(this)

/**
 * Convert an ISO-8601 time offset string in extended format into a [UtcOffset]
 */
fun String.toUtcOffset() = toUtcOffset(Iso8601.Extended.UTC_OFFSET_PARSER)

/**
 * Convert an ISO-8601 time offset string into a [UtcOffset] using a specific parser
 */
fun String.toUtcOffset(parser: DateTimeParser): UtcOffset {
    val result = parser.parse(this)
    return result.toUtcOffset() ?: raiseParserFieldResolutionException("UtcOffset", this)
}

/**
 * Resolve a parser result into a [UtcOffset]
 *
 * Required fields are [DateTimeField.UTC_OFFSET_ZERO] or [DateTimeField.UTC_OFFSET_SIGN] in conjunction with any
 * combination of [DateTimeField.UTC_OFFSET_HOURS], [DateTimeField.UTC_OFFSET_MINUTES], and
 * [DateTimeField.UTC_OFFSET_SECONDS].
 */
internal fun DateTimeParseResult.toUtcOffset(): UtcOffset? {
    val isZero = this[DateTimeField.UTC_OFFSET_ZERO] != null

    if (isZero) {
        return UtcOffset.ZERO
    }

    val sign = this[DateTimeField.UTC_OFFSET_SIGN]

    if (sign != null) {
        val hours = (this[DateTimeField.UTC_OFFSET_HOURS]?.toInt() ?: 0).hours
        val minutes = (this[DateTimeField.UTC_OFFSET_MINUTES]?.toInt() ?: 0).minutes
        val seconds = (this[DateTimeField.UTC_OFFSET_SECONDS]?.toInt() ?: 0).seconds

        return if (sign < 0L) {
            UtcOffset(-hours, -minutes, -seconds)
        } else {
            UtcOffset(hours, minutes, seconds)
        }
    }

    return null
}

internal const val MAX_UTC_OFFSET_STRING_LENGTH = 9

internal fun StringBuilder.appendUtcOffset(utcOffset: UtcOffset): StringBuilder {
    if (utcOffset.isZero) {
        append('Z')
    } else {
        utcOffset.toComponents { sign, hours, minutes, seconds ->
            append(if (sign < 0) '-' else '+')
            appendZeroPadded(hours.value, 2)
            append(':')
            appendZeroPadded(minutes.value, 2)

            if (seconds.value != 0) {
                append(':')
                appendZeroPadded(seconds.value, 2)
            }
        }
    }
    return this
}

private fun validateUtcOffsetComponents(hours: IntHours, minutes: IntMinutes, seconds: IntSeconds) {
    when {
        hours.isPositive -> if (minutes.isNegative || seconds.isNegative) {
            throw DateTimeException("Time offset minutes and seconds must be positive when hours are positive")
        }
        hours.isNegative -> if (minutes.isPositive || seconds.isPositive) {
            throw DateTimeException("Time offset minutes and seconds must be negative when hours are negative")
        }
        else -> if ((minutes.isNegative && seconds.isPositive) || (minutes.isPositive && seconds.isNegative)) {
            throw DateTimeException("Time offset minutes and seconds must have the same sign")
        }
    }

    if (hours.value !in -18..18) {
        throw DateTimeException("Time offset hours must be within +/-18, got '${hours.value}'")
    }
    if (minutes.value !in -59..59) {
        throw DateTimeException("Time offset minutes must be within +/-59, got '${minutes.value}'")
    }
    if (seconds.value !in -59..59) {
        throw DateTimeException("Time offset seconds must be within +/-59, got '${seconds.value}'")
    }
}