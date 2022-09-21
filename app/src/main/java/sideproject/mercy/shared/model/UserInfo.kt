package sideproject.mercy.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
	val userId: String,
	var idToken: String,
	var userName: String,
	var userEmail: String,
	var profilePictureUri: String
)