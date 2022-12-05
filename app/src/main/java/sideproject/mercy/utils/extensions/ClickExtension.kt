package sideproject.mercy.utils.extensions

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.databinding.BindingAdapter
import sideproject.mercy.utils.listener.DebounceClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun View.setOnDebounceClickListener(
    listener: View.OnClickListener
) {
    setOnClickListener(
        DebounceClickListener({
            listener.onClick(it)
        })
    )
}

fun View.setOnDebounceClickListener(
    listener: (View?) -> Unit,
    interval: Long = DebounceClickListener.DEFAULT_INTERVAL_TIME
) {
    setOnClickListener(
        DebounceClickListener(
            listener,
            interval
        )
    )
}

@BindingAdapter("onDebouncedClick", "interval", requireAll = false)
fun View.setOnDebouncedClick(callback: (() -> Unit)?, interval: Long) {
    setOnDebounceClickListener(
        listener = { callback?.invoke() },
        interval = if (interval > 0) interval else DebounceClickListener.DEFAULT_INTERVAL_TIME
    )
}

@BindingAdapter("onSingleClick", "onDoubleClick", requireAll = false)
fun View.onDoubleClick(singleClick: (() -> Unit)?, doubleClick: (() -> Unit)?) {

    val detector = GestureDetectorCompat(
        this.context,
        GestureDetector.SimpleOnGestureListener()
    )

    val touchListener = DebounceTouchListener(detector, singleClick, doubleClick)
    detector.setOnDoubleTapListener(touchListener)
    setOnTouchListener(touchListener)
}

class DebounceTouchListener(
    private val detector: GestureDetectorCompat,
    private val singleClick: (() -> Unit)?,
    private val doubleClick: (() -> Unit)?
) : View.OnTouchListener, GestureDetector.SimpleOnGestureListener() {
	override fun onDoubleTap(e: MotionEvent): Boolean {
		doubleClick?.invoke()
		return true
	}

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        debounce(scope = MainScope()) {
            singleClick?.invoke()
        }

        return true
    }

    override fun onTouch(view: View?, event: MotionEvent): Boolean {
        detector.onTouchEvent(event)
        return true
    }

    var debounceJob: Job? = null

    fun debounce(
        delayMillis: Long = 600L,
        scope: CoroutineScope,
        action: () -> Unit
    ) {
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action()
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}