package sideproject.mercy.domain

import sideproject.mercy.domain.Result.Error
import sideproject.mercy.domain.Result.Loading
import sideproject.mercy.domain.Result.Success

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

/**
 * Returns `true` if this instance represents a successful outcome.
 * In this case [isError] returns `false`.
 */
val Result<*>.isSuccess: Boolean get() = this is Success && data != null

/**
 * Returns `true` if this instance represents a failed outcome.
 * In this case [isSuccess] returns `false`.
 */
val Result<*>.isError: Boolean get() = this is Error

/**
 * if [Result.Success.data] exists return it or fallback
 */
inline fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Success<T>)?.data ?: fallback
}

/**
 * Returns the encapsulated value if this instance represents [success][Result.Success] or the
 * [defaultValue] if it is [failure][Result.isFailure].
 */
inline fun <R, T : R> Result<T>.getOrDefault(defaultValue: R): R {
    return (this as? Success<T>)?.data ?: defaultValue
}

/**
 * Returns [Result.data] or null
 */
val <T> Result<T>.data: T?
    get() = (this as? Success)?.data

inline fun <R, T> Result<T>.map(transform: (T) -> R): Result<R> {
    return when (this) {
        is Success -> Success(transform(data))
        is Error -> Error(exception)
        Loading -> Loading
    }
}

inline fun <R, T> Result<T>.mapCatching(transform: (T) -> R): Result<R> {
    return when (this) {
        is Success -> {
            try {
                Success(transform(data))
            } catch (e: Throwable) {
                Error(e)
            }
        }
        is Error -> Error(exception)
        Loading -> Loading
    }
}