package sideproject.mercy.presentation.ui.account.signin.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivitySignInBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.account.signin.model.SignInActionEntity
import sideproject.mercy.presentation.ui.account.signin.viewmodel.SignInViewModel
import sideproject.mercy.presentation.ui.interests.main.view.InterestsMainActivity
import sideproject.mercy.shared.authentication.manager.LoginManager
import sideproject.mercy.shared.authentication.manager.UserInfoManager
import sideproject.mercy.shared.authentication.observeChangedLogin
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySignInBinding
	private val viewModel by viewModels<SignInViewModel>()

	private lateinit var loginManager: LoginManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		loginManager = LoginManager(this)

		binding = ActivitySignInBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@SignInActivity
			viewModel = this@SignInActivity.viewModel
		}

		setContentView(binding.root)

		observeEventNotifier()
	}

	private fun observeEventNotifier() {
		observeHandledEvent(viewModel.event.click) {
			handleSelectEvent(it)
		}
		observeHandledEvent(viewModel.event.action) {
			handleActionEvent(it)
		}
		observeHandledEvent(viewModel.event.throwable) {
			if (it.first is UnknownHostException) {
				showToast(getString(R.string.error_default))
			}
		}
		observeChangedLogin { isSignedIn ->
			if (isSignedIn) {
				moveToNext()
			}
		}
	}

	private fun handleActionEvent(entity: ActionEntity) {
		when (entity) {
			is SignInActionEntity.SignInGoogle -> {
				if (UserInfoManager.isLoggedIn()) {
					loginManager.signOut {
						UserInfoManager.clearUserInfo()
						showToast("sign out")
					}
					return
				}

				loginManager.signIn()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToNext() {
		// Todo: 웰컴 페이지 보여줄지, 관심사 화면으로 바로 넘어갈지 분기처리 필요
		// moveToWelcomeActivity()
		moveToInterestsMainActivity()
	}

	private fun moveToWelcomeActivity() {

	}

	private fun moveToInterestsMainActivity() {
		val intent = Intent(this, InterestsMainActivity::class.java)
		startActivity(intent)
		finish()
	}
}