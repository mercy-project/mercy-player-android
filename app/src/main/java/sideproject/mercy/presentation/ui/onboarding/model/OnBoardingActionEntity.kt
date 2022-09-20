package sideproject.mercy.presentation.ui.onboarding.model

import sideproject.mercy.domain.entity.ActionEntity

sealed class OnBoardingActionEntity: ActionEntity() {
	object NextStep: OnBoardingActionEntity()
}