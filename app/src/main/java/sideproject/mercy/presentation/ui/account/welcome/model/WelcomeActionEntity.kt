package sideproject.mercy.presentation.ui.account.welcome.model

import sideproject.mercy.domain.entity.ActionEntity

sealed class WelcomeActionEntity : ActionEntity() {
	object MoveToSurvey : WelcomeActionEntity()
}