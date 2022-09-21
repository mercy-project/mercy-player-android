package sideproject.mercy.utils.time

import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import sideproject.mercy.shared.log.L
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code.JUST_NOW

object TimeUtils {
    private const val SEC = 60
    private const val MIN = 60
    private const val HOUR = 24
    private const val DAY = 30

    private val baseDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("y년 M월 d일")
    private val updateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("M월 d일 HH:mm")

    private fun nowInEpoch(): Long {
        return Instant.now().epochSecond
    }

    @JvmStatic
    fun timestampToString(
        timestamp: Long,
        formatter: DateTimeFormatter = baseDateFormatter
    ): String {
        return createTimeString(timestamp, formatter)
    }

    /**
     * Supports Unix timestamps in seconds and milliseconds.
     */
    @JvmStatic
    fun nowToString(formatter: DateTimeFormatter = updateTimeFormatter): String {
        val timestamp = nowInEpoch()

        return createTimeString(timestamp, formatter)
    }

    /**
     * SNS 형식 시간표시
     *  - 방금 전  : 1분 이하
     *  - ~일 전  : 30일 까지만 표시
     *  - 30일 이후 부터는 기존대로 y년 M월 d일 로 표시
     */
    @JvmStatic
    fun timestampToSnsFormat(timestamp: Long, timeStringProvider: TimeStringBaseProvider): String {
        val currentTime = Instant.now().toEpochMilli()

        val convertTimestamp = if (timestamp.toString().length <= 10) {
            Instant.ofEpochSecond(timestamp).toEpochMilli()
        } else {
            timestamp
        }

        var diffTime = (currentTime - convertTimestamp) / 1000

        return when {
            diffTime < SEC -> {
                timeStringProvider.getString(JUST_NOW)
            }

            SEC.let { diffTime /= it; diffTime } < MIN -> {
                val snsFormatter = timeStringProvider.getString(Code.MIN)
                String.format(snsFormatter, diffTime)
            }

            MIN.let { diffTime /= it; diffTime } < HOUR -> {
                val snsFormatter = timeStringProvider.getString(Code.HOUR)
                String.format(snsFormatter, diffTime)
            }

            HOUR.let { diffTime /= it; diffTime } <= DAY -> {
                val snsFormatter = timeStringProvider.getString(Code.DAY)
                String.format(snsFormatter, diffTime)
            }

            else -> {
                timestampToString(timestamp)
            }
        }
    }

    /**
     * Supports Unix timestamps in seconds.
     *
     * ex) 1591549457
     *      GMT: Sunday, June 7, 2020 5:04:17 PM
     *      Your time zone: Monday, June 8, 2020 2:04:17 AM GMT+09:00
     */
    fun ofEpochSecond(epochSecond: Long): ZonedDateTime? {
        val zoneId = ZoneId.systemDefault()
        val instant: Instant = Instant.ofEpochSecond(epochSecond)
        return ZonedDateTime.ofInstant(instant, zoneId)
    }

    /**
     * Supports Unix timestamps in milliseconds.
     *
     * ex) 1591549457000
     *      GMT: Sunday, June 7, 2020 5:04:17 PM
     *      Your time zone: Monday, June 8, 2020 2:04:17 AM GMT+09:00
     */
    fun ofEpochMilliseconds(epochMilli: Long): ZonedDateTime? {
        val zoneId = ZoneId.systemDefault()
        val instant: Instant = Instant.ofEpochMilli(epochMilli)
        return ZonedDateTime.ofInstant(instant, zoneId)
    }

    private fun secondToString(
        epochSecond: Long,
        formatter: DateTimeFormatter = baseDateFormatter
    ): String = formatter.format(ofEpochSecond(epochSecond))

    private fun millisecondToString(
        epochMilli: Long,
        formatter: DateTimeFormatter = baseDateFormatter
    ): String = formatter.format(ofEpochMilliseconds(epochMilli))

    private fun createTimeString(
        timestamp: Long,
        formatter: DateTimeFormatter
    ): String {
        return if (timestamp.toString().length > 10) {
            millisecondToString(
                epochMilli = timestamp,
                formatter = formatter
            )
        } else {
            secondToString(
                epochSecond = timestamp,
                formatter = formatter
            )
        }
    }

    /**
     * zonedDateTime 형식의 날짜를 받으면 LocalDate 로 변환
     *
     * @param zonedDateTime "2010-09-20T00:00:00.000+09:00"
     * @return [String] "2010-09-20"
     */
    fun zonedDateTimeToLocalDate(zonedDateTime: String) = try {
        ZonedDateTime.parse(zonedDateTime).toLocalDate().toString()
    } catch (e: Exception) {
        L.e(e)
        ""
    }
}