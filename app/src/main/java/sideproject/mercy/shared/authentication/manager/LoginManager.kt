package sideproject.mercy.shared.authentication.manager

import android.app.Activity.RESULT_OK
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sideproject.mercy.BuildConfig
import sideproject.mercy.utils.extensions.showToast
import timber.log.Timber

class LoginManager (private val activity: ComponentActivity) {

	// Configure sign-in to request the user's ID, email address, and basic
	// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
	private val gso: GoogleSignInOptions by lazy {
		GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestIdToken(BuildConfig.GOOGLE_WEB_CLIENT_ID)
			.requestEmail()
			.build()
	}

	private val mGoogleSignInClient: GoogleSignInClient by lazy {
		GoogleSignIn.getClient(activity, gso)
	}

	private val auth: FirebaseAuth by lazy {
		Firebase.auth
	}

	private val signInLauncher = activity.registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.resultCode == RESULT_OK) {
			val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
			handleSignInResult(task)

			try {
				val account = task.getResult(ApiException::class.java)
				firebaseAuthWithGoogle(account!!.idToken)
			} catch (e: ApiException) {
				activity.showToast("Failed Google Login")
			}
		}
	}

	/**
	 * Google 유저의 token을 사용하여 Firebase에 인증합니다.
	 */
	private fun firebaseAuthWithGoogle(idToken: String?) {
		val credential = GoogleAuthProvider.getCredential(idToken, null)
		auth.signInWithCredential(credential)
			.addOnCompleteListener { task ->
				if (task.isSuccessful) {
					Timber.i("firebase authentication successful.")
				}
			}
	}

	/**
	 * GoogleSignInAccount 객체에는 사용자의 이름과 같이 로그인한 사용자에 대한 정보가 포함됩니다.
	 */
	private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
		try {
			val account = completedTask.getResult(ApiException::class.java)

			// 로그인 성공시 유저 정보 저장
			saveUserInfo(account)
		} catch (e: ApiException) {
			// The ApiException status code indicates the detailed failure reason.
			// Please refer to the GoogleSignInStatusCodes class reference for more information.
			Timber.w("signInResult:failed code=" + e.statusCode)
			saveUserInfo(null)
		}
	}

	private fun saveUserInfo(account: GoogleSignInAccount?) {
		account?.run {
			UserInfoManager.updateUserInfoByGoogleSignInAccount(this)
		}

		Timber.d("Account id: ${account?.id.orEmpty()}, email: ${account?.email}, idToken: ${account?.idToken}")
	}

	fun signIn() {
		val signInIntent = mGoogleSignInClient.signInIntent
		signInLauncher.launch(signInIntent)
	}

	fun signOut(updateAction: () -> Unit) {
		mGoogleSignInClient.signOut()
			.addOnCompleteListener {
				updateAction()
			}
	}

	/**
	 * 계정 연결 해제
	 * Google로 로그인 한 사용자에게 앱에서 Google 계정 연결을 해제 할 수있는 기능을 제공하는 것이 좋습니다.
	 * 사용자가 계정을 삭제하는 경우 앱이 Google API에서 얻은 정보를 삭제해야합니다.
	 */
	fun revokeAccess(updateAction: () -> Unit) {
		mGoogleSignInClient.revokeAccess()
			.addOnCompleteListener {
				updateAction()
			}
	}
}