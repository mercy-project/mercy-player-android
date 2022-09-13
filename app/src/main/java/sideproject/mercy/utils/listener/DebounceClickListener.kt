package sideproject.mercy.utils.listener

import android.os.SystemClock
import android.view.View

/**
 * Debounce Click Listener
 */
class DebounceClickListener(
    private val listener: (View?) -> Unit,
    private val interval: Long = DEFAULT_INTERVAL_TIME
) : View.OnClickListener {
    private var lastClickTime: Long = 0

    override fun onClick(v: View?) {
        performClick { listener(v) }
    }

    private fun performClick(callback: () -> Unit) {
        SystemClock.elapsedRealtime().let { time ->
            if ((time - lastClickTime) < interval) {
                return
            }
            lastClickTime = time
            callback()
        }
    }

    companion object {
        const val DEFAULT_INTERVAL_TIME = 500L
    }
}