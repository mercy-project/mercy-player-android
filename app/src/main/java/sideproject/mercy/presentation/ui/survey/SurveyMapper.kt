package sideproject.mercy.presentation.ui.survey

object SurveyMapper {

	fun getSurveyState(survey: Survey): SurveyState {

		val questions: List<QuestionState> = survey.questions.mapIndexed { index, question ->
			val showPrevious = index > 0
			val showDone = index == survey.questions.size -1
			QuestionState(
				question = question,
				questionIndex = index,
				totalQuestionsCount = survey.questions.size,
				showPrevious = showPrevious,
				showDone = showDone
			)
		}

		return SurveyState.Questions(survey.title, questions)
	}
}