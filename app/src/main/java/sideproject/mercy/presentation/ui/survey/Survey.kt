package sideproject.mercy.presentation.ui.survey

data class SurveyResult(
	val result: String
)

data class Survey(
	val title: String,
	val questions: List<Question>
)

data class Question(
	val id: Int,
	val questionText: String,
	val answer: PossibleAnswer,
	val description: String? = null
)

/**
 * 답변 가능한 Type
 */
sealed class PossibleAnswer {
	data class SingleChoice(val optionAnswers: List<OptionAnswer>) : PossibleAnswer()
	data class MultipleChoice(val optionsAnswers: List<OptionAnswer>) : PossibleAnswer()
}

data class OptionAnswer(
	val answerId: Int,
	val answer: String
)

/**
 * 실제 답변을 저장
 */
sealed class Answer<T : PossibleAnswer> {
	data class SingleChoice(
		val answerId: Int
	) : Answer<PossibleAnswer.SingleChoice>()

	data class MultipleChoice(
		val answersIds: Set<Int>
	) : Answer<PossibleAnswer.MultipleChoice>()
}

/**
 * 답변이 선택되었는지 또는 선택 해제되었는지에 따라 선택한 답변 목록에서 답변을 추가하거나 제거합니다.
 */
fun Answer.MultipleChoice.withAnswerSelected(
	answerId: Int,
	selected: Boolean
): Answer.MultipleChoice {
	val answerIds = answersIds.toMutableSet()
	if (selected) {
		answerIds.add(answerId)
	} else {
		answerIds.remove(answerId)
	}
	return Answer.MultipleChoice(answerIds)
}