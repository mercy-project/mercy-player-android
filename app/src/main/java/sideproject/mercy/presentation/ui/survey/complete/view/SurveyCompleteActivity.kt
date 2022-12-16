package sideproject.mercy.presentation.ui.survey.complete.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivitySurveyCompleteBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.main.view.MainActivity
import sideproject.mercy.presentation.ui.survey.complete.model.SurveyCompleteActionEntity
import sideproject.mercy.presentation.ui.survey.complete.viewmodel.SurveyCompleteViewModel
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class SurveyCompleteActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySurveyCompleteBinding

	private val viewModel by viewModels<SurveyCompleteViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivitySurveyCompleteBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@SurveyCompleteActivity
			viewModel = this@SurveyCompleteActivity.viewModel
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
			is SurveyCompleteActionEntity.MoveToMain -> {
				moveToMain()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToMain() {
		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
		finish()
	}

	companion object {
		fun start(context: Context) {
			context.startActivity(
				Intent(context, SurveyCompleteActivity::class.java)
			)
		}
	}
}