package sideproject.mercy.presentation.ui.survey.complete.model

import sideproject.mercy.domain.entity.ActionEntity

sealed class SurveyCompleteActionEntity : ActionEntity() {
	object MoveToMain : SurveyCompleteActionEntity()
}