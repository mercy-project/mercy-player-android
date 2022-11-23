package sideproject.mercy.data.repository

import sideproject.mercy.data.model.mercy.UserInfoResponse

interface UserRepository {
	suspend fun checkHealth(): Boolean
	suspend fun getUserInfo(userId: String): UserInfoResponse
}