package sideproject.mercy.presentation.ui.survey

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sideproject.mercy.presentation.common.theme.MercyTheme

@Composable
fun Question(
	question: Question,
	answer: Answer<*>?,
	onAnswer: (Answer<*>) -> Unit,
	modifier: Modifier = Modifier
) {

	val scrollState = rememberScrollState()
	Column(
		modifier = modifier
			.padding(horizontal = 20.dp)
			.verticalScroll(scrollState)
	) {
		Spacer(modifier = Modifier.height(44.dp))
		val backgroundColor = if (MaterialTheme.colors.isLight) {
			MaterialTheme.colors.onSurface.copy(alpha = 0.04f)
		} else {
			MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
		}

		Row(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					color = backgroundColor,
					shape = MaterialTheme.shapes.small
				)
		) {
			Text(
				text = question.questionText,
				style = MaterialTheme.typography.subtitle1,
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						vertical = 24.dp,
						horizontal = 16.dp
					)
			)
		}

		Spacer(modifier = Modifier.height(24.dp))
		if (question.description != null) {
			CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
				Text(
					text = question.description,
					style = MaterialTheme.typography.caption,
					modifier = Modifier
						.fillMaxWidth()
						.padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
				)
			}
		}

		when (question.answer) {
			is PossibleAnswer.MultipleChoice -> MultipleChoiceQuestion(
				possibleAnswer = question.answer,
				answer = answer as Answer.MultipleChoice?,
				onAnswerSelected = { answerId, selected ->
					if (answer == null) {
						onAnswer(Answer.MultipleChoice(setOf(answerId)))
					} else {
						onAnswer(answer.withAnswerSelected(answerId, selected))
					}
				},
				modifier = Modifier.fillMaxWidth()
			)

			else -> {}
		}

		Spacer(modifier = Modifier.height(24.dp))
	}

}

@Composable
private fun MultipleChoiceQuestion(
	possibleAnswer: PossibleAnswer.MultipleChoice,
	answer: Answer.MultipleChoice?,
	onAnswerSelected: (Int, Boolean) -> Unit,
	modifier: Modifier = Modifier
) {
	Column(modifier = modifier) {
		for (optionAnswer in possibleAnswer.optionsAnswers) {
			var checkedState by remember {
				val selectedOption = answer?.answersIds?.contains(optionAnswer.answerId)
				mutableStateOf(selectedOption ?: false)
			}

			Surface(
				shape = MaterialTheme.shapes.small,
				border = BorderStroke(
					width = 1.dp,
					color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
				),
				modifier = Modifier.padding(vertical = 4.dp)
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.clickable(
							onClick = {
								checkedState = !checkedState
								onAnswerSelected(optionAnswer.answerId, checkedState)
							}
						)
						.padding(vertical = 16.dp, horizontal = 24.dp),
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(text = optionAnswer.answer)

					Checkbox(
						checked = checkedState,
						onCheckedChange = { selected ->
							checkedState = selected
							onAnswerSelected(optionAnswer.answerId, selected)
						},
						colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
					)
				}
			}
		}
	}
}

@Preview
@Composable
fun QuestionPreview() {
	val question = Question(
		id = 2,
		questionText = "평상시에 동영상은\n언제 시청하시나요?",
		answer = PossibleAnswer.MultipleChoice(
			optionsAnswers = listOf(
				OptionAnswer(0, "아침시간 (오전 6시 - 오전 11시)"),
				OptionAnswer(1,"점심시간 (오전 12시 - 오후 1시)"),
				OptionAnswer(2,"해가 있는 오후시간 (오후 2시 - 오후 5시)"),
				OptionAnswer(3,"저녁시간 (오후 6시 - 오후 9시)"),
				OptionAnswer(4,"잠자기전 (오후 10시 - 오전 3시)"),
				OptionAnswer(5,"잠잘때 (오전 4시 ~ 오전 5시)"),
				OptionAnswer(6,"잠잘때 (오전 4시 ~ 오전 5시)"),
				OptionAnswer(7,"잠잘때 (오전 4시 ~ 오전 5시)"),
				OptionAnswer(8,"잠잘때 (오전 4시 ~ 오전 5시)"),
				OptionAnswer(9,"잠잘때 (오전 4시 ~ 오전 5시)"),
			)
		),
		description = "최대 2개까지 선택가능합니다\n설정한 시간에서 영상을 추천해드립니다"
	)

	MercyTheme {
		Question(question = question, answer = null, onAnswer = {})
	}
}
