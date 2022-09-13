// Origin : https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150

package sideproject.mercy.utils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations


/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class SingleContentEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
open class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
    Observer<SingleContentEvent<T>> {
    override fun onChanged(event: SingleContentEvent<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

class MutableLiveHandledData<T> : MutableLiveData<SingleContentEvent<T>>() {
    fun setHandledValue(newValue: T) {
        value = SingleContentEvent(newValue)
    }

    fun postHandledValue(newValue: T) {
        postValue(SingleContentEvent(newValue))
    }
}

typealias LiveHandledData<T> = LiveData<SingleContentEvent<T>>
typealias MutableHandledSingleEvent = MutableLiveData<SingleContentEvent<Unit>>
typealias HandledSingleEvent = LiveData<SingleContentEvent<Unit>>
typealias SingleContentObserver<T> = Observer<SingleContentEvent<T>>


fun MutableHandledSingleEvent.post() {
    this.value = SingleContentEvent(Unit)
}

inline fun <T, Y> LiveHandledData<T>.mapHandledData(crossinline transformer: (T) -> Y)
        : LiveHandledData<Y> {
    return Transformations.map(this) {
        it?.getContentIfNotHandled()?.let { value ->
            SingleContentEvent(transformer(value))
        }
    }
}
