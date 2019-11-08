package io.islandtime.ranges

import io.islandtime.*
import io.islandtime.MAX_INSTANT_STRING_LENGTH
import io.islandtime.appendInstant
import io.islandtime.measures.*
import io.islandtime.parser.*
import io.islandtime.ranges.internal.buildIsoString
import io.islandtime.toInstant
import kotlin.random.Random

/**
 * A half-open interval of instants
 */
class InstantInterval(
    start: Instant = Instant.MIN,
    endExclusive: Instant = Instant.MAX
) : TimePointInterval<Instant>(start, endExclusive) {

    override val hasUnboundedStart: Boolean get() = start == Instant.MIN
    override val hasUnboundedEnd: Boolean get() = endExclusive == Instant.MAX

    override fun toString() = buildIsoString(MAX_INSTANT_STRING_LENGTH, StringBuilder::appendInstant)

    companion object {
        /**
         * An empty interval
         */
        val EMPTY = InstantInterval(Instant.UNIX_EPOCH, Instant.UNIX_EPOCH)

        /**
         * An unbounded (ie. infinite) interval
         */
        val UNBOUNDED = InstantInterval(Instant.MIN, Instant.MAX)

        internal fun withInclusiveEnd(
            start: Instant,
            endInclusive: Instant
        ): InstantInterval {
            val endExclusive = if (endInclusive == Instant.MAX) {
                endInclusive
            } else {
                endInclusive + 1.nanoseconds
            }

            return InstantInterval(start, endExclusive)
        }
    }
}

/**
 * Convert an ISO-8601 time interval between two UTC date-times in extended format into an [InstantInterval].
 */
fun String.toInstantInterval() = toInstantInterval(DateTimeParsers.Iso.Extended.INSTANT_INTERVAL)

/**
 * Convert a string into an [InstantInterval] using the provided parser.
 */
fun String.toInstantInterval(parser: GroupedDateTimeParser): InstantInterval {
    val results = parser.parse(this).expectingGroupCount<InstantInterval>(2, this)

    val start = when {
        results[0].isEmpty() -> null
        results[0].fields[DateTimeField.IS_UNBOUNDED] == 1L -> InstantInterval.UNBOUNDED.start
        else -> results[0].toInstant() ?: throwParserFieldResolutionException<InstantInterval>(this)
    }

    val end = when {
        results[1].isEmpty() -> null
        results[1].fields[DateTimeField.IS_UNBOUNDED] == 1L -> InstantInterval.UNBOUNDED.endExclusive
        else -> results[1].toInstant() ?: throwParserFieldResolutionException<InstantInterval>(this)
    }

    return when {
        start != null && end != null -> start until end
        start == null && end == null -> InstantInterval.EMPTY
        else -> throw DateTimeParseException("Intervals with unknown start or end are not supported")
    }
}

/**
 * Return a random instant within the interval using the default random number generator.
 */
fun InstantInterval.random(): Instant = random(Random)

/**
 * Return a random instant within the interval using the supplied random number generator.
 */
fun InstantInterval.random(random: Random): Instant {
    try {
        return Instant.fromUnixEpochSecond(
            random.nextLong(start.unixEpochSecond, endExclusive.unixEpochSecond),
            random.nextInt(start.unixEpochNanoOfSecond, endExclusive.unixEpochNanoOfSecond)
        )
    } catch (e: IllegalArgumentException) {
        throw NoSuchElementException(e.message)
    }
}

/**
 * Get an interval containing all of the instants up to, but not including [to].
 */
infix fun Instant.until(to: Instant) = InstantInterval(this, to)

/**
 * Convert a range of dates into an [InstantInterval] between the starting and ending instants in a particular time
 * zone.
 */
fun DateRange.asInstantIntervalAt(zone: TimeZone): InstantInterval {
    return when {
        isEmpty() -> InstantInterval.EMPTY
        isUnbounded -> InstantInterval.UNBOUNDED
        else -> {
            val start = if (hasUnboundedStart) Instant.MIN else start.startOfDayAt(zone).asInstant()
            val end = if (hasUnboundedEnd) Instant.MAX else endInclusive.endOfDayAt(zone).asInstant()
            start..end
        }
    }
}

/**
 * Convert an [OffsetDateTimeInterval] into an [InstantInterval].
 */
fun OffsetDateTimeInterval.asInstantInterval(): InstantInterval {
    return when {
        isEmpty() -> InstantInterval.EMPTY
        isUnbounded -> InstantInterval.UNBOUNDED
        else -> {
            val startInstant = if (hasUnboundedStart) Instant.MIN else start.asInstant()
            val endInstant = if (hasUnboundedEnd) Instant.MAX else endExclusive.asInstant()
            startInstant until endInstant
        }
    }
}

/**
 * Convert a [ZonedDateTimeInterval] until an [InstantInterval].
 */
fun ZonedDateTimeInterval.asInstantInterval(): InstantInterval {
    return when {
        isEmpty() -> InstantInterval.EMPTY
        isUnbounded -> InstantInterval.UNBOUNDED
        else -> {
            val startInstant = if (hasUnboundedStart) Instant.MIN else start.asInstant()
            val endInstant = if (hasUnboundedEnd) Instant.MAX else endExclusive.asInstant()
            startInstant until endInstant
        }
    }
}