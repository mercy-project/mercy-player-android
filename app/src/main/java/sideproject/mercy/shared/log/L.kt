package sideproject.mercy.shared.log

import com.ys.basicandroid.common.log.timber.CrashlyticsTree
import sideproject.mercy.BuildConfig
import timber.log.Timber

/**
 * Logger
 *
 * Timber wrapper
 */
object L {

    @JvmStatic
    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree())
    }

    @JvmStatic
    fun v(msg: String?) {
        Timber.v(msg)
    }

    @JvmStatic
    fun v(tag: String, msg: String?) {
        Timber.tag(tag).v(msg)
    }

    @JvmStatic
    fun d(msg: String?) {
        Timber.d(msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String?) {
        Timber.tag(tag).d(msg)
    }

    @JvmStatic
    fun d(throwable: Throwable) {
        Timber.d(throwable)
    }

    @JvmStatic
    fun d(throwable: Throwable, msg: String?) {
        Timber.d(throwable, msg)
    }

    @JvmStatic
    fun d(tag: String, throwable: Throwable) {
        Timber.tag(tag).d(throwable)
    }

    @JvmStatic
    fun d(tag: String, throwable: Throwable, msg: String?) {
        Timber.tag(tag).d(throwable, msg)
    }

    @JvmStatic
    fun i(msg: String?) {
        Timber.i(msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String?) {
        Timber.tag(tag).i(msg)
    }

    @JvmStatic
    fun w(msg: String?) {
        Timber.w(msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String?) {
        Timber.tag(tag).w(msg)
    }

    @JvmStatic
    fun e(msg: String?) {
        Timber.e(msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String?) {
        Timber.tag(tag).e(msg)
    }

    @JvmStatic
    fun e(throwable: Throwable) {
        Timber.e(throwable)
    }

    @JvmStatic
    fun e(throwable: Throwable, msg: String?) {
        Timber.e(throwable, msg)
    }

    @JvmStatic
    fun e(tag: String, throwable: Throwable) {
        Timber.tag(tag).e(throwable)
    }

    @JvmStatic
    fun e(tag: String, throwable: Throwable, msg: String?) {
        Timber.tag(tag).e(throwable, msg)
    }

    object Crashlytics {
        @JvmStatic
        fun setCustomKey(key: String, value: String): Crashlytics {
	        CrashlyticsLog.setCustomKey(key, value)
            return this
        }
    }
}