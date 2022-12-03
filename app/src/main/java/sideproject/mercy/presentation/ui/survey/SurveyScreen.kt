package sideproject.mercy.presentation.ui.survey

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import sideproject.mercy.R
import sideproject.mercy.presentation.common.theme.progressIndicatorBackground
import sideproject.mercy.utils.extensions.orFalse

@Composable
fun SurveyQuestionsScreen(
	questions: SurveyState.Questions,
	onDonePressed: () -> Unit,
	onBackPressed: () -> Unit
) {
	val questionState = remember(questions.currentQuestionIndex) {
		questions.questionsState[questions.currentQuestionIndex]
	}

	Surface(modifier = Modifier.fillMaxSize()) {
		Scaffold(
			topBar = {
				SurveyTopAppBar(
					questionIndex = questionState.questionIndex,
					totalQuestionsCount = questionState.totalQuestionsCount,
					onBackPressed = onBackPressed
				)
			},
			content = { innerPadding ->
				Question(
					question = questionState.question,
					answer = questionState.answer,
					onAnswer = {
						questionState.answer = it
						questionState.enableNext = (it as? Answer.MultipleChoice)?.answersIds?.isNotEmpty().orFalse()
					},
					modifier = Modifier
						.fillMaxSize()
						.padding(innerPadding)
				)
			},
			bottomBar = {
				SurveyBottomBar(
					questionState = questionState,
					onPreviousPressed = { questions.currentQuestionIndex-- },
					onNextPressed = { questions.currentQuestionIndex++ },
					onDonePressed = onDonePressed
				)
			}
		)
	}
}

@Composable
private fun TopAppBarTitle(
	questionIndex: Int,
	totalQuestionsCount: Int,
	modifier: Modifier = Modifier
) {
	val indexStyle = MaterialTheme.typography.caption.toSpanStyle().copy(
		fontWeight = FontWeight.Bold
	)
	val totalStyle = MaterialTheme.typography.caption.toSpanStyle()
	val text = buildAnnotatedString {
		withStyle(style = indexStyle) {
			append("${questionIndex + 1}")
		}
		withStyle(style = totalStyle) {
			append(stringResource(id = R.string.question_count, totalQuestionsCount))
		}
	}
	Text(
		text = text,
		style = MaterialTheme.typography.caption,
		modifier = modifier
	)
}

@Composable
private fun SurveyTopAppBar(
	questionIndex: Int,
	totalQuestionsCount: Int,
	onBackPressed: () -> Unit
) {
	ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
		val (button, text, progress) = createRefs()
		TopAppBarTitle(
			questionIndex = questionIndex,
			totalQuestionsCount = totalQuestionsCount,
			modifier = Modifier
				.padding(vertical = 20.dp)
				.constrainAs(text) {
					centerHorizontallyTo(parent)
				}
		)

		CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
			IconButton(
				onClick = onBackPressed,
				modifier = Modifier
					.padding(horizontal = 12.dp)
					.constrainAs(button) {
						end.linkTo(parent.end)
					}
			) {
				Icon(Icons.Filled.Close, null)
			}
		}

		LinearProgressIndicator(
			progress = (questionIndex + 1) / totalQuestionsCount.toFloat(),
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 20.dp)
				.constrainAs(progress) {
					bottom.linkTo(text.bottom)
				},
			backgroundColor = MaterialTheme.colors.progressIndicatorBackground()
		)
	}
}

@Composable
private fun SurveyBottomBar(
	questionState: QuestionState,
	onPreviousPressed: () -> Unit,
	onNextPressed: () -> Unit,
	onDonePressed: () -> Unit
) {
	Surface(
		elevation = 3.dp,
		modifier = Modifier.fillMaxWidth()
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 20.dp)
		) {
			if (questionState.showPrevious) {
				OutlinedButton(
					modifier = Modifier.weight(1f),
					onClick = onPreviousPressed
				) {
					Text(text = stringResource(id = R.string.previous))
				}
				Spacer(modifier = Modifier.width(16.dp))
			}

			if (questionState.showDone) {
				Button(
					modifier = Modifier.weight(1f),
					onClick = onDonePressed,
					enabled = questionState.enableNext
				) {
					Text(text = stringResource(id = R.string.done))
				}
			} else {
				Button(
					modifier = Modifier.weight(1f),
					onClick = onNextPressed,
					enabled = questionState.enableNext
				) {
					Text(text = stringResource(id = R.string.next))
				}
			}

		}
	}
}