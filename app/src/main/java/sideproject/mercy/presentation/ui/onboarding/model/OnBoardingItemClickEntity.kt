package sideproject.mercy.presentation.ui.onboarding.model

import sideproject.mercy.domain.entity.ClickEntity

sealed class OnBoardingItemClickEntity : ClickEntity() {
	object NextItem : OnBoardingItemClickEntity()
}