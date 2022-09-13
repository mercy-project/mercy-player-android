package sideproject.mercy.utils.time

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.temporal.ChronoUnit

class TimeUtilsTest {
    private lateinit var timeStringProvider: TimeStringProviderStub

    /**
     * Your time zone: Monday, June 8, 2020 2:04:17 AM GMT+09:00
     */
    private val expectedDateString = "2020년 6월 8일"

    private val epochSecond: Long = 1591549457
    private val epochMilli: Long = 1591549457000

    @Before
    fun setUp() {
        timeStringProvider = TimeStringProviderStub()
    }

    @Test
    fun `세컨드 변환`() {
        val zonedDateTime = TimeUtils.ofEpochSecond(epochSecond)
        assertZoneDateTime(zonedDateTime)
    }

    @Test
    fun `밀리세컨드 변환`() {
        val zonedDateTime = TimeUtils.ofEpochMilliseconds(epochMilli)
        assertZoneDateTime(zonedDateTime)
    }

    private fun assertZoneDateTime(zonedDateTime: ZonedDateTime?) {
        assertNotNull(zonedDateTime)
        assertEquals(zonedDateTime?.year, 2020)
        assertEquals(zonedDateTime?.monthValue, 6)
        assertEquals(zonedDateTime?.dayOfMonth, 8)
    }

    @Test
    fun `자동으로 구분(세컨드를 날짜 문자열로 바꾸기)`() {
        assertDateString(TimeUtils.timestampToString(epochSecond))
    }

    @Test
    fun `자동으로 구분(밀리세컨드를 날짜 문자열로 바꾸기)`() {
        assertDateString(TimeUtils.timestampToString(epochMilli))
    }

    private fun assertDateString(dateString: String) {
        assertNotNull(dateString)
        assertEquals(dateString, expectedDateString)
    }

    @Test
    fun simpleTest() {
        val end = TimeUtils.ofEpochSecond(1609308360)

        val today = ZonedDateTime.now()

        println("end: $end, start: $today")
        println("remainSeconds: ${today.until(end, ChronoUnit.SECONDS)}")
    }

    @Test
    fun `SNS Format - 방금 전`() {
        val today = ZonedDateTime.now()
        println("beforeTime: $today")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = today.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, "방금 전")
    }

    private val before1Minutes = ZonedDateTime.now().minusMinutes(1)
    private val before1MinutesExpected = "1분 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '1분 전'이 제대로 나온다`() {
        println("beforeTime: $before1Minutes")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Minutes.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1MinutesExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '1분 전'이 제대로 나온다`() {
        println("beforeTime: $before1Minutes")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Minutes.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1MinutesExpected)
    }

    private val before59Minutes = ZonedDateTime.now().minusMinutes(59)
    private val before59MinutesExpected = "59분 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '59분 전'이 제대로 나온다`() {
        println("beforeTime: $before59Minutes")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before59Minutes.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before59MinutesExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '59분 전'이 제대로 나온다`() {
        println("beforeTime: $before59Minutes")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before59Minutes.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before59MinutesExpected)
    }

    private val before1Hour = ZonedDateTime.now().minusHours(1)
    private val before1HourExpected = "1시간 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '1시간 전'이 제대로 나온다`() {
        println("beforeTime: $before1Hour")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Hour.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1HourExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '1시간 전'이 제대로 나온다`() {
        println("beforeTime: $before1Hour")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Hour.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1HourExpected)
    }

    private val before23Hour = ZonedDateTime.now().minusHours(23)
    private val before23HourExpected = "23시간 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '23시간 전'이 제대로 나온다`() {
        println("beforeTime: $before23Hour")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before23Hour.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before23HourExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '23시간 전'이 제대로 나온다`() {
        println("beforeTime: $before23Hour")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before23Hour.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before23HourExpected)
    }

    private val before1Day = ZonedDateTime.now().minusDays(1)
    private val before1DayExpected = "1일 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '1일 전'이 제대로 나온다`() {
        println("beforeTime: $before1Day")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Day.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1DayExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '1일 전'이 제대로 나온다`() {
        println("beforeTime: $before1Day")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1Day.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1DayExpected)
    }

    private val before30Day = ZonedDateTime.now().minusDays(30)
    private val before30DayExpected = "30일 전"

    @Test
    fun `SNS Format - epochSecond 가 주어질때 '30일 전'이 제대로 나온다`() {
        println("beforeTime: $before30Day")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before30Day.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before30DayExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 '30일 전'이 제대로 나온다`() {
        println("beforeTime: $before30Day")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before30Day.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before30DayExpected)
    }

    private val before1MonthOver = ZonedDateTime.now().minusDays(31)
    private val before1MonthOverExpected =
        TimeUtils.timestampToString(before1MonthOver.toEpochSecond())

    @Test
    fun `SNS Format - epochSecond 가 주어질때 30일 이후는 y년 M월 d일 format 으로 나온다`() {
        println("beforeTime: $before1MonthOver")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1MonthOver.toEpochSecond(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1MonthOverExpected)
    }

    @Test
    fun `SNS Format - epochMilli 가 주어질때 30일 이후는 y년 M월 d일 format 으로 나온다`() {
        println("beforeTime: $before1MonthOver")
        val actual = TimeUtils.timestampToSnsFormat(
            timestamp = before1MonthOver.toInstant().toEpochMilli(),
            timeStringProvider = timeStringProvider
        )

        assertEquals(actual, before1MonthOverExpected)
    }

    @Test
    fun `zonedDateTime 형식으로 시간을 받으면 localDate 으로 변환`() {
        val zonedDateString = "2010-09-20T00:00:00.000+09:00"
        val localDateTime = TimeUtils.zonedDateTimeToLocalDate(zonedDateString)
        assertEquals("2010-09-20", localDateTime)
    }
}