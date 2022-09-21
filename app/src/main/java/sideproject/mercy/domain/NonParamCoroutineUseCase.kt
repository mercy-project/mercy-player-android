package sideproject.mercy.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import sideproject.mercy.domain.Result.Error
import sideproject.mercy.domain.Result.Success
import sideproject.mercy.shared.log.L

abstract class NonParamCoroutineUseCase<R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
	            Success(execute())
            }
        } catch (e: Exception) {
            L.e(e)
            Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(): R
}