package sideproject.mercy.presentation.ui.account.signin.view

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivitySignInBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.common.AppConfig
import sideproject.mercy.presentation.ui.account.signin.model.SignInActionEntity
import sideproject.mercy.presentation.ui.account.signin.viewmodel.SignInViewModel
import sideproject.mercy.presentation.ui.account.welcome.view.WelcomeActivity
import sideproject.mercy.presentation.ui.survey.SurveyActivity
import sideproject.mercy.presentation.ui.webview.view.WebViewActivity
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

		initView()
		observeEventNotifier()
	}

	private fun initView() {
		setUnderlineForTerms()
	}

	private fun setUnderlineForTerms() {
		binding.tvTermsOfService.paintFlags = Paint.UNDERLINE_TEXT_FLAG
		binding.tvPrivacyPolicy.paintFlags = Paint.UNDERLINE_TEXT_FLAG
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
				viewModel.checkNextStep()
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

			is SignInActionEntity.TermsOfService -> {
				moveToWebView(
					title = getString(R.string.terms_of_service),
					url = AppConfig.URL.TERMS_OF_SERVICE_URL

				)
			}

			is SignInActionEntity.PrivacyPolicy -> {
				moveToWebView(
					title = getString(R.string.privacy_policy),
					url = AppConfig.URL.PRIVACY_POLICY_URL
				)
			}

			is SignInActionEntity.MoveToWelcome -> {
				moveToWelcome()
			}

			is SignInActionEntity.MoveToSurvey -> {
				moveToSurvey()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToWebView(title: String, url: String) {
		WebViewActivity.startActivity(
			context = this,
			title = title,
			url = url
		)
	}

	private fun moveToWelcome() {
		WelcomeActivity.start(this)
		finish()
	}

	private fun moveToSurvey() {
		SurveyActivity.start(this)
		finish()
	}
}