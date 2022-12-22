package sideproject.mercy.presentation.ui.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import com.google.android.material.timepicker.MaterialTimePicker
import sideproject.mercy.R
import sideproject.mercy.presentation.common.theme.MercyTheme
import sideproject.mercy.presentation.ui.survey.complete.view.SurveyCompleteActivity
import sideproject.mercy.utils.extensions.showToast

class SurveyActivity : AppCompatActivity() {

	private val viewModel: SurveyViewModel by viewModels {
		SurveyViewModelFactory()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			MercyTheme {
				viewModel.uiState.observeAsState().value?.let { surveyState ->
					when (surveyState) {
						is SurveyState.Questions -> SurveyQuestionsScreen(
							questions = surveyState,
							onAction = { id, action -> handleSurveyAction(id, action) },
							onExceedLimit = { showExceedLimitToast(it) },
							onDonePressed = {
								viewModel.computeResult(surveyState)
							},
							onBackPressed = {
								onBackPressedDispatcher.onBackPressed()
							}
						)
						is SurveyState.Result -> moveToSurveyComplete()
					}
				}
			}
		}
	}

	private fun handleSurveyAction(questionId: Int, actionType: SurveyActionType) {
		when (actionType) {
			SurveyActionType.PICK_TIME -> showDatePicker(questionId)
		}
	}

	private fun showDatePicker(questionId: Int) {
		val picker = MaterialTimePicker.Builder().build()
		picker.show(supportFragmentManager, picker.toString())
		picker.addOnPositiveButtonClickListener {
			viewModel.onTimePicked(questionId, "${picker.hour}:${picker.minute}")
		}
	}

	private fun showExceedLimitToast(limit: Int) {
		val exceedLimitMessage = String.format(getString(R.string.format_selected_limit), limit)
		showToast(exceedLimitMessage)
	}

	private fun moveToSurveyComplete() {
		SurveyCompleteActivity.start(this)
		finish()
	}

	companion object {
		fun start(context: Context) {
			context.startActivity(
				Intent(context, SurveyActivity::class.java)
			)
		}
	}
}