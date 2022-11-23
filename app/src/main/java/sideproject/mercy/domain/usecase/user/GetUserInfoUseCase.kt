package sideproject.mercy.domain.usecase.user

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import sideproject.mercy.data.model.mercy.UserInfoResponse
import sideproject.mercy.data.repository.UserRepository
import sideproject.mercy.domain.UseCase
import sideproject.mercy.domain.usecase.user.GetUserInfoUseCase.Params
import sideproject.mercy.shared.IoDispatcher

class GetUserInfoUseCase @Inject constructor(
	private val userRepository: UserRepository,
	@IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Params, UserInfoResponse>(dispatcher) {

	override suspend fun execute(param: Params): UserInfoResponse {
		return userRepository.getUserInfo(param.userId)
	}

	data class Params(
		val userId: String
	)
}