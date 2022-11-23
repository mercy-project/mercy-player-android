package sideproject.mercy.domain.model

data class UserInfo(
	val firebaseUid: String,
	val mercyId: String,
	val fcmToken: String,
	val email: String,
	val usuallyWatchTime: List<String>,
	val videoSpentMinute: String,
	val favorGenre: List<String>,
	val userStatus: String,
	val createdAt: String,
	val updatedAt: String
)