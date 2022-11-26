package sideproject.mercy.presentation.ui.survey

data class SurveyResult(
	val result: String,
)

data class Survey(
	val title: String,
	val questions: List<Question>
)

data class Question(
	val id: Int,
	val questionText: String,
	val answer: PossibleAnswer,
	val description: Int? = null
)

/**
 * 답변 가능한 Type
 */
sealed class PossibleAnswer {
	data class SingleChoice(val optionsStringRes: List<Int>) : PossibleAnswer()
	data class MultipleChoice(val optionsStringRes: List<Int>) : PossibleAnswer()
}

/**
 * 실제 답변을 저장
 */
sealed class Answer<T : PossibleAnswer> {
	data class SingleChoice(
		val answer: Int
	) : Answer<PossibleAnswer.SingleChoice>()

	data class MultipleChoice(
		val answersStringRes: Set<Int>
	) : Answer<PossibleAnswer.MultipleChoice>()
}

/**
 * 답변이 선택되었는지 또는 선택 해제되었는지에 따라 선택한 답변 목록에서 답변을 추가하거나 제거합니다.
 */
fun Answer.MultipleChoice.withAnswerSelected(
	answer: Int,
	selected: Boolean
): Answer.MultipleChoice {
	val newStringRes = answersStringRes.toMutableSet()
	if (selected) {
		newStringRes.add(answer)
	} else {
		newStringRes.remove(answer)
	}
	return Answer.MultipleChoice(newStringRes)
}