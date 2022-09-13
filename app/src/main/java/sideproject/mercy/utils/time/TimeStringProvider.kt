package sideproject.mercy.utils.time

import android.content.Context

class TimeStringProvider constructor(val context: Context) : TimeStringBaseProvider() {
    override fun getStringRes(id: Int): String {
        return context.getString(id)
    }
}