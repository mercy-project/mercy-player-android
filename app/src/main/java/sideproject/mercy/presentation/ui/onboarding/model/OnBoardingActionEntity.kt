package sideproject.mercy.presentation.ui.onboarding.model

import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.presentation.ui.onboarding.item.OnBoardingItemViewModel

sealed class OnBoardingActionEntity: ActionEntity() {
	class UpdateList(val list: List<OnBoardingItemViewModel>): OnBoardingActionEntity()
	object NextStep: OnBoardingActionEntity()
}