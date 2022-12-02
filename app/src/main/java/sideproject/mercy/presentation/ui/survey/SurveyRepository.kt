package sideproject.mercy.presentation.ui.survey

import sideproject.mercy.presentation.ui.survey.PossibleAnswer.MultipleChoice

private val mercyQuestions = mutableListOf(
	Question(
		id = 1,
		questionText = "동영상 관심 분야를\n3개 선택해주세요",
		answer = MultipleChoice(
			optionsStrings = listOf(
				"Science & Technology 과학/기술",
				"Music 음악",
				"Travel 여행",
				"Cook 요리",
				"Education 교육",
				"Pets & Animals 애완동물",
				"Entertainment 엔터테인먼트",
				"Movies & Animation 영화/ 애니메이션",
			)
		)
	),
	Question(
		id = 2,
		questionText = "평상시에 동영상은\n언제 시청하시나요?",
		answer = MultipleChoice(
			optionsStrings = listOf(
				"아침시간 (오전 6시 - 오전 11시)",
				"점심시간 (오전 12시 - 오후 1시)",
				"해가 있는 오후시간 (오후 2시 - 오후 5시)",
				"저녁시간 (오후 6시 - 오후 9시)",
				"잠자기전 (오후 10시 - 오전 3시)",
				"잠잘때 (오전 4시 ~ 오전 5시)",
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