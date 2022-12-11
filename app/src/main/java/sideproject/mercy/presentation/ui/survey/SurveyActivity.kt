package sideproject.mercy.presentation.ui.survey

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.livedata.observeAsState
import sideproject.mercy.presentation.common.theme.MercyTheme
import sideproject.mercy.presentation.ui.main.view.MainActivity

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
							onDonePressed = {
								viewModel.computeResult(surveyState)
							},
							onBackPressed = {
								onBackPressedDispatcher.onBackPressed()
							}
						)
						is SurveyState.Result -> moveToMainActivity()
					}
				}
			}
		}
	}

	private fun moveToMainActivity() {
		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
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