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
import sideproject.mercy.presentation.ui.main.view.MainActivity
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySignInBinding
	private val viewModel by viewModels<SignInViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

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
	}

	private fun handleActionEvent(entity: ActionEntity) {
		when (entity) {
			is SignInActionEntity.SignInGoogle -> {
				moveToNextActivity()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToNextActivity() {

		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
		finish()
	}
}