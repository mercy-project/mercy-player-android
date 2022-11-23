package sideproject.mercy.data.repository

import javax.inject.Inject
import sideproject.mercy.data.api.MercyApi
import sideproject.mercy.data.model.mercy.UserInfoResponse

class UserRepositoryImpl @Inject constructor(
	private val mercyApi: MercyApi
) : UserRepository {
	override suspend fun checkHealth(): Boolean {
		return mercyApi.checkHealth().isAlive
	}

	override suspend fun getUserInfo(userId: String): UserInfoResponse {
		return mercyApi.getUserInfo(userId)
	}
}