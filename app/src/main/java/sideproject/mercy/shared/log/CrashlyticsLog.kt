package sideproject.mercy.shared.log

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

/**
 * Firebase Crashlytics Logger
 */
object CrashlyticsLog {
    @JvmStatic
    fun recordException(throwable: Throwable) {
        Firebase.crashlytics.recordException(throwable)
    }

    @JvmStatic
    fun log(message: String) {
        Firebase.crashlytics.log(message)
    }

    @JvmStatic
    fun setCustomKey(key: String, value: String) {
        Firebase.crashlytics.setCustomKey(key, value)
    }
}