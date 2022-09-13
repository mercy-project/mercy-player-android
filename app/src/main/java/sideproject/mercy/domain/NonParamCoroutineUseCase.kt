package sideproject.mercy.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import sideproject.mercy.domain.Result.Error
import sideproject.mercy.domain.Result.Success
import timber.log.Timber

abstract class NonParamCoroutineUseCase<R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute().let {
                    Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}