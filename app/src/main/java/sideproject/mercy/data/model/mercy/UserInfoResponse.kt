package sideproject.mercy.data.model.mercy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
	@SerialName("firebaseUid")
	val firebaseUid: String?,

	@SerialName("mercyId")
	val mercyId: String?,

	@SerialName("fcmToken")
	val fcmToken: String?,

	@SerialName("email")
	val email: String?,

	@SerialName("usuallyWatchTime")
	val usuallyWatchTime: List<String>?,

	@SerialName("videoSpentMinute")
	val videoSpentMinute: String?,

	@SerialName("favorGenre")
	val favorGenre: List<String>?,

	@SerialName("userStatus")
	val userStatus: String?,

	@SerialName("createdAt")
	val createdAt: String?,

	@SerialName("updatedAt")
	val updatedAt: String?
)
