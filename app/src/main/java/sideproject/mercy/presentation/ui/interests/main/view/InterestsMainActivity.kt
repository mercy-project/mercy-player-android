package sideproject.mercy.presentation.ui.interests.main.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivityInterestsMainBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.interests.main.model.InterestsMainActionEntity
import sideproject.mercy.presentation.ui.interests.main.viewmodel.InterestsMainViewModel
import sideproject.mercy.presentation.ui.main.view.MainActivity
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class InterestsMainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityInterestsMainBinding
	private val viewModel by viewModels<InterestsMainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityInterestsMainBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@InterestsMainActivity
			viewModel = this@InterestsMainActivity.viewModel
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
			is InterestsMainActionEntity.NextStep -> {
				moveToMainActivity()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	private fun moveToMainActivity() {

		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
		finish()
	}
}