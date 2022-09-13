package sideproject.mercy.utils.time

import sideproject.mercy.R
import sideproject.mercy.presentation.res.IStringResourceGetter
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code.DAY
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code.HOUR
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code.JUST_NOW
import sideproject.mercy.utils.time.TimeStringBaseProvider.Code.MIN

abstract class TimeStringBaseProvider : IStringResourceGetter {
    enum class Code {
        JUST_NOW,
        MIN,
        HOUR,
        DAY,
    }

    /**
     * 문구를 변경해야 하면 TimeStringBaseProvider 를 상속받아서 이 부분을 새로 구현
     */
    open fun getString(code: Code): String {
        return when (code) {
            JUST_NOW -> getStringRes(R.string.format_sns_time_just_now)
            MIN -> getStringRes(R.string.format_sns_time_min)
            HOUR -> getStringRes(R.string.format_sns_time_hour)
            DAY -> getStringRes(R.string.format_sns_time_day)
        }
    }
}