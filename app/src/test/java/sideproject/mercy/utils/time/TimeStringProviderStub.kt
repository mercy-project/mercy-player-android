package sideproject.mercy.utils.time

import sideproject.mercy.R

class TimeStringProviderStub : TimeStringBaseProvider() {
    override fun getStringRes(id: Int): String {
        return when (id) {
            R.string.format_sns_time_just_now -> {
                "방금 전"
            }

            R.string.format_sns_time_min -> {
                "%s분 전"
            }

            R.string.format_sns_time_hour -> {
                "%s시간 전"
            }

            R.string.format_sns_time_day -> {
                "%s일 전"
            }

            else -> {
                ""
            }
        }
    }
}