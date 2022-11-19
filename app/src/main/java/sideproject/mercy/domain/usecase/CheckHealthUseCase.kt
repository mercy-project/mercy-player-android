package sideproject.mercy.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import sideproject.mercy.data.api.MercyApi
import sideproject.mercy.domain.NonParamCoroutineUseCase
import sideproject.mercy.shared.IoDispatcher

class CheckHealthUseCase @Inject constructor(
	private val mercyApi: MercyApi,
	@IoDispatcher dispatcher: CoroutineDispatcher
) : NonParamCoroutineUseCase<Boolean>(dispatcher) {
	override suspend fun execute(): Boolean {
		return mercyApi.checkHealth().isAlive
	}
}