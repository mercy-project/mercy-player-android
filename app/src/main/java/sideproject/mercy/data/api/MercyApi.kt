package sideproject.mercy.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import sideproject.mercy.data.model.mercy.HealthResponse
import sideproject.mercy.data.model.mercy.UserInfoResponse

interface MercyApi {
	@GET("/")
	suspend fun checkHealth(): HealthResponse

	@GET("/user/{user_id}")
	suspend fun getUserInfo(
		@Path("user_id") userId: String
	): UserInfoResponse
}