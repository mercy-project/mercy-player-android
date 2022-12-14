package sideproject.mercy.presentation.ui.survey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class QuestionState(
	val question: Question,
	val questionIndex: Int,
	val totalQuestionsCount: Int,
	val showPrevious: Boolean,
	val showDone: Boolean
) {
	var enableNext by mutableStateOf(false)
	var answer by mutableStateOf<Answer<*>?>(null)
}

sealed class SurveyState {
	data class Questions(
		val surveyTitle: String,
		val questionsState: List<QuestionState>
	) : SurveyState() {
		var currentQuestionIndex by mutableStateOf(0)
	}

	data class Result(
		val surveyTitle: String,
		val surveyResult: SurveyResult
	) : SurveyState()
}