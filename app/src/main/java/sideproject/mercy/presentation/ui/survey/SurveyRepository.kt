package sideproject.mercy.presentation.ui.survey

import sideproject.mercy.presentation.ui.survey.PossibleAnswer.MultipleChoice

private val mercyQuestions = mutableListOf(
	Question(
		id = 1,
		questionText = "동영상 관심 분야를\n3개 선택해주세요",
		answer = MultipleChoice(
			optionsAnswers = listOf(
				OptionAnswer(0, "Science & Technology 과학/기술"),
				OptionAnswer(1, "Music 음악"),
				OptionAnswer(2, "Travel 여행"),
				OptionAnswer(3, "Cook 요리"),
				OptionAnswer(4, "Education 교육"),
				OptionAnswer(5, "Pets & Animals 애완동물"),
				OptionAnswer(6, "Entertainment 엔터테인먼트"),
				OptionAnswer(7, "Movies & Animation 영화/ 애니메이션")
			)
		)
	),
	Question(
		id = 2,
		questionText = "평상시에 동영상은\n언제 시청하시나요?",
		answer = MultipleChoice(
			optionsAnswers = listOf(
				OptionAnswer(10, "아침시간 (오전 6시 - 오전 11시)"),
				OptionAnswer(11,"점심시간 (오전 12시 - 오후 1시)"),
				OptionAnswer(12,"해가 있는 오후시간 (오후 2시 - 오후 5시)"),
				OptionAnswer(13,"저녁시간 (오후 6시 - 오후 9시)"),
				OptionAnswer(14,"잠자기전 (오후 10시 - 오전 3시)"),
				OptionAnswer(15,"잠잘때 (오전 4시 ~ 오전 5시)")
			)
		),
		description = "최대 2개까지 선택가능합니다\n설정한 시간에서 영상을 추천해드립니다"
	)
)

private val mercySurvey = Survey(
	title = "동영상 시청 설문조사",
	questions = mercyQuestions
)

object SurveyRepository {

	suspend fun getSurvey() = mercySurvey

	fun sendSurveyResult(answers: List<Answer<*>>): SurveyResult {
		return SurveyResult(
			result = "Success"
		)
	}
}