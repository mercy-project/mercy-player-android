package com.ys.basicandroid.common.log.timber

import android.util.Log
import sideproject.mercy.shared.log.CrashlyticsLog
import sideproject.mercy.shared.log.timber.StackTraceRecorder
import timber.log.Timber

/**
 * Firebase Crashlytics Tree
 *
 * Log.ERROR 는 recordException 으로 나머지는 log 로 Firebase Crashlytics 에 기록
 */
class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val msg = if (tag != null) "[$tag] $message" else message
        if (priority == Log.ERROR) {
            CrashlyticsLog.recordException(
                if (t != null) {
                    StackTraceRecorder(msg, t)
                } else {
                    StackTraceRecorder(msg)
                }
            )
        } else {
            CrashlyticsLog.log(msg)
        }
    }
}