package sideproject.mercy.shared.authentication.manager

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import sideproject.mercy.shared.model.UserInfo

object UserInfoMapper {

	fun getUserInfo(googleSignInAccount: GoogleSignInAccount): UserInfo {
		return UserInfo(
			userId = googleSignInAccount.id.orEmpty(),
			idToken = googleSignInAccount.idToken.orEmpty(),
			userName = googleSignInAccount.displayName.orEmpty(),
			userEmail = googleSignInAccount.email.orEmpty(),
			profilePictureUri = googleSignInAccount.photoUrl.toString()
		)
	}

	fun getUserInfo(firebaseUser: FirebaseUser): UserInfo {
		return UserInfo(
			userId = firebaseUser.uid,
			idToken = firebaseUser.getIdToken(false).result.token.orEmpty(),
			userName = firebaseUser.displayName.orEmpty(),
			userEmail = firebaseUser.email.orEmpty(),
			profilePictureUri = firebaseUser.photoUrl?.toString().orEmpty()
		)
	}
}