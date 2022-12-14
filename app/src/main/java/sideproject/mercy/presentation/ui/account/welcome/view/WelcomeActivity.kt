package sideproject.mercy.presentation.ui.account.welcome.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivityWelcomeBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.account.welcome.model.WelcomeActionEntity
import sideproject.mercy.presentation.ui.account.welcome.viewmodel.WelcomeViewModel
import sideproject.mercy.presentation.ui.survey.SurveyActivity
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

	private lateinit var binding: ActivityWelcomeBinding

	private val viewModel by viewModels<WelcomeViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityWelcomeBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@WelcomeActivity
			viewModel = this@WelcomeActivity.viewModel
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
			is WelcomeActionEntity.MoveToSurvey -> {
				moveToSurvey()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToSurvey() {
		SurveyActivity.start(this)
		finish()
	}

	companion object {
		fun start(context: Context) {
			context.startActivity(
				Intent(context, WelcomeActivity::class.java)
			)
		}
	}
}