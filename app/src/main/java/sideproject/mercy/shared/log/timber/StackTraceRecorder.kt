package sideproject.mercy.shared.log.timber

import sideproject.mercy.shared.log.L
import timber.log.Timber

/**
 * StackTraceRecorder
 *
 * Firebase Crashlytics 상에서 표시되는 Stack Trace 에 Timber 및 L 관련 내용 제거
 */
class StackTraceRecorder(
	message: String? = null,
	cause: Throwable? = null
) : Exception(message, cause) {

    override fun fillInStackTrace(): Throwable {
        super.fillInStackTrace()
        val iterator = stackTrace.iterator()
        val filtered = arrayListOf<StackTraceElement>()

        while (iterator.hasNext()) {
            val element = iterator.next()
            if (isIgnoreElement(element)) {
                break
            }
        }

        var isPassed = false
        while (iterator.hasNext()) {
            val element = iterator.next()
            if (!isPassed && isIgnoreElement(element)) {
                continue
            }
            isPassed = true
            filtered.add(element)
        }

        stackTrace = filtered.toTypedArray()
        return this
    }

    private fun isIgnoreElement(element: StackTraceElement): Boolean {
        return when (element.className) {
            Timber::class.java.name, L::class.java.name -> true
            else -> false
        }
    }
}