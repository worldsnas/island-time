package dev.erikchristensen.islandtime

import dev.erikchristensen.islandtime.interval.*
import dev.erikchristensen.islandtime.parser.DateTimeParseException
import dev.erikchristensen.islandtime.parser.Iso8601
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TimeTest {
    @Test
    fun `throws an exception when constructed with an invalid hour`() {
        assertFailsWith<DateTimeException> { Time(-1, 0) }
        assertFailsWith<DateTimeException> { Time(24, 0) }
    }

    @Test
    fun `throws an exception when constructed with an invalid minute`() {
        assertFailsWith<DateTimeException> { Time(0, -1) }
        assertFailsWith<DateTimeException> { Time(0, 60) }
    }

    @Test
    fun `throws an exception when constructed with an invalid second`() {
        assertFailsWith<DateTimeException> { Time(0, 0, -1) }
        assertFailsWith<DateTimeException> { Time(0, 0, 60) }
    }

    @Test
    fun `throws an exception when constructed with an invalid nanosecond`() {
        assertFailsWith<DateTimeException> { Time(0, 0, 0, -1) }
        assertFailsWith<DateTimeException> { Time(0, 0, 0, 1_000_000_000) }
    }

    @Test
    fun `Time_fromSecondOfDay() creates a Time from a second of the day value`() {
        assertEquals(
            Time(1,1,1,1),
            Time.fromSecondOfDay(3661, 1)
        )
    }

    @Test
    fun `Time_fromNanosecondOfDay() creates a Time from a nanosecond of the day value`() {
        assertEquals(
            Time(0, 0, 1, 1),
            Time.fromNanosecondOfDay(1_000_000_001L)
        )
    }

    @Test
    fun `secondOfDay property`() {
        assertEquals(0, Time(0, 0, 0).secondOfDay)
        assertEquals(86_399, Time(23, 59, 59).secondOfDay)
    }

    @Test
    fun `nanosecondOfDay property`() {
        assertEquals(0, Time(0, 0, 0, 0).nanosecondOfDay)
        assertEquals(
            86_399_999_999_999L,
            Time(23, 59, 59, 999_999_999).nanosecondOfDay
        )
    }

    @Test
    fun `secondsSinceStartOfDay property`() {
        assertEquals(1.seconds, Time(0,0,1,1).secondsSinceStartOfDay)
    }

    @Test
    fun `nanosecondsSinceStartOfDay property`() {
        assertEquals(1L.nanoseconds, Time(0,0,0,1).nanosecondsSinceStartOfDay)
    }

    @Test
    fun `toString() returns an ISO-8601 extended time with minute precision`() {
        assertEquals("00:00", Time(0, 0, 0, 0).toString())
    }

    @Test
    fun `toString() returns an ISO-8601 extended time with second precision`() {
        assertEquals("01:01:01", Time(1, 1, 1, 0).toString())
    }

    @Test
    fun `toString() returns an ISO-8601 extended time with millisecond precision`() {
        assertEquals("01:01:01.100", Time(1, 1, 1, 100_000_000).toString())
    }

    @Test
    fun `toString() returns an ISO-8601 extended time with microsecond precision`() {
        assertEquals("23:59:59.000990", Time(23, 59, 59, 990_000).toString())
    }

    @Test
    fun `toString() returns an ISO-8601 extended time with nanosecond precision`() {
        assertEquals(
            "23:59:59.000000900",
            Time(23, 59, 59, 900).toString()
        )
    }

    @Test
    fun `can be broken down into components`() {
        val (hour, minute, second, nano) = Time(1, 2, 3, 4)
        assertEquals(1, hour)
        assertEquals(2, minute)
        assertEquals(3, second)
        assertEquals(4, nano)
    }

    @Test
    fun `copy() returns a Time with altered values`() {
        assertEquals(Time(3, 30), Time(9, 30).copy(hour = 3))
        assertEquals(Time(9, 1), Time(9, 30).copy(minute = 1))
        assertEquals(Time(9, 30, 3), Time(9, 30).copy(second = 3))
        assertEquals(
            Time(9, 30, 0, 3),
            Time(9, 30).copy(nanosecond = 3)
        )
    }

    @Test
    fun `truncatedToHours() removes components smaller than hours`() {
        assertEquals(
            Time(1, 0),
            Time(1,2,3,4).truncatedToHours()
        )
    }

    @Test
    fun `truncatedToMinutes() removes components smaller than minutes`() {
        assertEquals(
            Time(1, 2),
            Time(1,2,3,4).truncatedToMinutes()
        )
    }

    @Test
    fun `truncatedToSeconds() removes components smaller than seconds`() {
        assertEquals(
            Time(1, 2, 3),
            Time(1,2,3,4).truncatedToSeconds()
        )
    }

    @Test
    fun `truncatedToMilliseconds() removes components smaller than milliseconds`() {
        assertEquals(
            Time(1, 2, 3, 444_000_000),
            Time(1,2,3,444_555_666).truncatedToMilliseconds()
        )
    }

    @Test
    fun `truncatedToMicroseconds() removes components smaller than microseconds`() {
        assertEquals(
            Time(1, 2, 3, 444_555_000),
            Time(1,2,3,444_555_666).truncatedToMicroseconds()
        )
    }

    @Test
    fun `adding zero of any unit doesn't affect the time`() {
        assertEquals(
            Time(1, 1,1,1),
            Time(1, 1, 1, 1) +
                0.hours + 0.minutes + 0.seconds + 0.milliseconds + 0.microseconds + 0.nanoseconds
        )

        assertEquals(
            Time(1, 1,1,1),
            Time(1, 1, 1, 1) +
                0L.hours + 0L.minutes + 0L.seconds + 0L.milliseconds + 0L.microseconds + 0L.nanoseconds
        )
    }

    @Test
    fun `subtracting zero of any unit doesn't affect the time`() {
        assertEquals(
            Time(1, 1,1,1),
            Time(1, 1, 1, 1) -
                0.hours - 0.minutes - 0.seconds - 0.milliseconds - 0.microseconds - 0.nanoseconds
        )

        assertEquals(
            Time(1, 1,1,1),
            Time(1, 1, 1, 1) -
                0L.hours - 0L.minutes - 0L.seconds - 0L.milliseconds - 0L.microseconds - 0L.nanoseconds
        )
    }

    @Test
    fun `add positive hours`() {
        assertEquals(Time(1, 0), Time(23, 0) + 2.hours)
        assertEquals(Time(8,0), Time(1, 0) + Long.MAX_VALUE.hours)
    }

    @Test
    fun `add negative hours`() {
        assertEquals(Time(23, 30, 12), Time(2, 30, 12) + (-3).hours)
        assertEquals(Time.MIDNIGHT, Time(8, 0) + Long.MIN_VALUE.hours)
    }

    @Test
    fun `subtract positive hours`() {
        assertEquals(Time(23, 30, 12), Time(2, 30, 12) - 3.hours)
        assertEquals(Time.MIDNIGHT, Time(7, 0) - Long.MAX_VALUE.hours)
    }

    @Test
    fun `subtract negative hours`() {
        assertEquals(Time(1, 0), Time(23, 0) - (-2).hours)
        assertEquals(Time(9,0), Time(1, 0) - Long.MIN_VALUE.hours)
    }

    @Test
    fun `add positive minutes`() {
        assertEquals(
            Time(0, 1),
            Time(23, 59) + 2.minutes
        )
    }

    @Test
    fun `add negative minutes`() {
        assertEquals(
            Time(23, 59),
            Time(0, 1) + (-2).minutes
        )
    }

    @Test
    fun `subtract positive minutes`() {
        assertEquals(
            Time(23, 59),
            Time(0, 1) - 2.minutes
        )
    }

    @Test
    fun `subtract negative minutes`() {
        assertEquals(
            Time(0, 1),
            Time(23, 59) - (-2).minutes
        )
    }

    @Test
    fun `add positive seconds`() {
        assertEquals(
            Time(0, 0, 1),
            Time(23, 59, 59) + 2.seconds
        )
    }

    @Test
    fun `add negative seconds`() {
        assertEquals(
            Time(23, 59, 59),
            Time(0, 0, 1) + (-2).seconds
        )
    }

    @Test
    fun `subtract positive seconds`() {
        assertEquals(
            Time(23, 59, 59),
            Time(0, 0, 1) - 2.seconds
        )
    }

    @Test
    fun `subtract negative seconds`() {
        assertEquals(
            Time(0, 0, 1),
            Time(23, 59, 59) - (-2).seconds
        )
    }

    @Test
    fun `add positive milliseconds`() {
        assertEquals(
            Time(0,0,0, 100_111_111),
            Time(23, 59, 59, 900_111_111) + 200.milliseconds
        )

        assertEquals(
            Time.fromNanosecondOfDay(25_975_807_000_000L),
            Time.MIDNIGHT + Long.MAX_VALUE.milliseconds
        )
    }

    @Test
    fun `add negative milliseconds`() {
        assertEquals(
            Time(23, 59, 59, 900_111_111),
            Time(0,0,0, 100_111_111) + (-200).milliseconds
        )

        assertEquals(
            Time.fromNanosecondOfDay(60_424_192_000_000L),
            Time.MIDNIGHT + Long.MIN_VALUE.milliseconds
        )
    }

    @Test
    fun `subtract positive milliseconds`() {
        assertEquals(
            Time(23, 59, 59, 900_111_111),
            Time(0,0,0, 100_111_111) - 200.milliseconds
        )

        assertEquals(
            Time.fromNanosecondOfDay(60_424_193_000_000L),
            Time.MIDNIGHT - Long.MAX_VALUE.milliseconds
        )
    }

    @Test
    fun `subtract negative milliseconds`() {
        assertEquals(
            Time(0,0,0, 100_111_111),
            Time(23, 59, 59, 900_111_111) - (-200).milliseconds
        )

        assertEquals(
            Time.fromNanosecondOfDay(25_975_808_000_000L),
            Time.MIDNIGHT - Long.MIN_VALUE.milliseconds
        )
    }

    @Test
    fun `add positive microseconds`() {
        assertEquals(
            Time(0,0,0, 100_111),
            Time(23, 59, 59, 999_900_111) + 200.microseconds
        )
    }

    @Test
    fun `add negative microseconds`() {
        assertEquals(
            Time(23, 59, 59, 999_900_111),
            Time(0,0,0, 100_111) + (-200).microseconds
        )
    }

    @Test
    fun `subtract positive microseconds`() {
        assertEquals(
            Time(23, 59, 59, 999_900_111),
            Time(0,0,0, 100_111) - 200.microseconds
        )
    }

    @Test
    fun `subtract negative microseconds`() {
        assertEquals(
            Time(0,0,0, 100_111),
            Time(23, 59, 59, 999_900_111) - (-200).microseconds
        )
    }

    @Test
    fun `add positive nanoseconds`() {
        assertEquals(
            Time(0,0,0, 100),
            Time(23, 59, 59, 999_999_900) + 200.nanoseconds
        )
    }

    @Test
    fun `add negative nanoseconds`() {
        assertEquals(
            Time(23, 59, 59, 999_999_900),
            Time(0,0,0, 100) + (-200).nanoseconds
        )
    }

    @Test
    fun `subtract positive nanoseconds`() {
        assertEquals(
            Time(23, 59, 59, 999_999_900),
            Time(0,0,0, 100) - 200.nanoseconds
        )
    }

    @Test
    fun `subtract negative nanoseconds`() {
        assertEquals(
            Time(0,0,0, 100),
            Time(23, 59, 59, 999_999_900) - (-200).nanoseconds
        )
    }

    @Test
    fun `String_toTime() throws an exception when given an empty string`() {
        assertFailsWith<DateTimeParseException> { "".toTime() }
        assertFailsWith<DateTimeParseException> { "".toTime(Iso8601.TIME_PARSER) }
    }

    @Test
    fun `String_toTime() parses valid ISO-8601 extended time strings by default`() {
        assertEquals(Time(2, 0), "02".toTime())
        assertEquals(Time(23, 0), "23:00".toTime())
        assertEquals(Time(23, 30, 5), "23:30:05".toTime())
        assertEquals(Time(23, 30, 5, 100_000), "23:30:05.0001".toTime())
    }

    @Test
    fun `String_toTime() parses valid strings with explicit parser`() {
        assertEquals(Time(2, 0), "02".toTime(Iso8601.Basic.TIME_PARSER))
        assertEquals(Time(23, 0), "2300".toTime(Iso8601.Basic.TIME_PARSER))
        assertEquals(Time(23, 30, 5), "233005".toTime(Iso8601.Basic.TIME_PARSER))
        assertEquals(
            Time(23, 30, 5, 100_000),
            "233005.0001".toTime(Iso8601.Basic.TIME_PARSER)
        )
    }
}