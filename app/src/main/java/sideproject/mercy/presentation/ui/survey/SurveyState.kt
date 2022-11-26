package sideproject.mercy.presentation.ui.survey

class QuestionState(
	val question: Question,
	val questionIndex: Int,
	val totalQuestionsCount: Int,
	val showPrevious: Boolean,
	val showDone: Boolean
)