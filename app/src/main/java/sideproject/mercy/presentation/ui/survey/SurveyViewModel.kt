package sideproject.mercy.presentation.ui.survey

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SurveyViewModel(
	private val surveyRepository: SurveyRepository
) : ViewModel() {

	private val _uiState = MutableLiveData<SurveyState>()
	val uiState: LiveData<SurveyState>
		get() = _uiState

	private lateinit var surveyInitialState: SurveyState

	init {
		viewModelScope.launch {
			val survey = surveyRepository.getSurvey()
			surveyInitialState = SurveyMapper.getSurveyState(survey)
			_uiState.value = surveyInitialState
		}
	}

	fun computeResult(surveyQuestions: SurveyState.Questions) {
		val answers = surveyQuestions.questionsState.mapNotNull { it.answer }
		val result = surveyRepository.sendSurveyResult(answers)
		_uiState.value = SurveyState.Result(surveyQuestions.surveyTitle, result)
	}

	private fun getLatestQuestionId(): Int? {
		val latestState = _uiState.value
		if (latestState != null && latestState is SurveyState.Questions) {
			return latestState.questionsState[latestState.currentQuestionIndex].question.id
		}

		return null
	}
}

class SurveyViewModelFactory(): ViewModelProvider.Factory {
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
			return SurveyViewModel(SurveyRepository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}