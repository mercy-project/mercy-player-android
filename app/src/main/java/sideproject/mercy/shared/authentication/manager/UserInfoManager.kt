package sideproject.mercy.shared.authentication.manager

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import javax.inject.Singleton
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sideproject.mercy.shared.log.L
import sideproject.mercy.shared.model.UserInfo
import sideproject.mercy.shared.storage.PreferenceStorage

@Singleton
class UserInfoManager private constructor() {

	private var info: UserInfo? = null
		get() {
			return if (field == null || field?.idToken.isNullOrBlank()) {
				field = getUserData()
				return field
			} else {
				field
			}
		}
		set(value) {
			if (value == null) {
				removeUserInfo()
			} else {
				putUserInfo(value)
			}
			field = value
		}

	private fun putUserInfo(userInfo: UserInfo) {
		userInfo.asJsonString().let {
			preferences?.put(KEY_USER_INFO, it)
		}
	}

	private fun getUserData(): UserInfo? {
		val result = preferences?.getValue(KEY_USER_INFO, null)

		return if (result.isNullOrEmpty()) {
			L.d(TAG, "userInfo is null or empty")
			null
		} else {
			result.asUserInfo()
		}
	}

	private fun removeUserInfo() {
		preferences?.remove(KEY_USER_INFO)
	}

	private fun String.asUserInfo(): UserInfo? {
		return try {
			Json.decodeFromString<UserInfo>(this)
		} catch (e: Exception) {
			L.e(TAG, e)
			null
		}
	}

	private fun UserInfo.asJsonString(): String? {
		return try {
			Json.encodeToString(this)
		} catch (e: Exception) {
			L.e(TAG, e)
			null
		}
	}

	companion object {
		private const val TAG = "UserInfoManager"
		private const val KEY_USER_INFO = "key_user_info"

		private var instance: UserInfoManager? = null

		private var preferences: PreferenceStorage? = null

		private fun getInstance(): UserInfoManager {
			return instance ?: UserInfoManager().also {
				instance = it
			}
		}

		@JvmStatic
		fun init(context: Context) {
			preferences = PreferenceStorage(context)
		}

		@JvmStatic
		fun isLoggedIn() = getInstance().info != null

		@JvmStatic
		fun getUserInfo() = getInstance().info

		@JvmStatic
		fun getIdToken() = getInstance().info?.idToken

		@JvmStatic
		fun getUserName() = getInstance().info?.userName

		@JvmStatic
		fun getEmail() = getInstance().info?.userEmail

		@JvmStatic
		fun updateUserInfo(useInfo: UserInfo?) {
			useInfo?.run {
				getInstance().info?.let { info ->
					info.idToken.let {
						if (this.idToken.isEmpty() && it.isNotEmpty()) {
							this.idToken = it
						}
					}
				}
			}

			getInstance().info = useInfo
		}

		@JvmStatic
		fun updateUserInfoByFirebaseUser(firebaseUser: FirebaseUser) {
			updateUserInfo(UserInfoMapper.getUserInfo(firebaseUser))
		}

		@JvmStatic
		fun updateUserInfoByGoogleSignInAccount(googleSignInAccount: GoogleSignInAccount) {
			updateUserInfo(UserInfoMapper.getUserInfo(googleSignInAccount))
		}

		@JvmStatic
		fun clearUserInfo() {
			getInstance().info = null
		}
	}
}