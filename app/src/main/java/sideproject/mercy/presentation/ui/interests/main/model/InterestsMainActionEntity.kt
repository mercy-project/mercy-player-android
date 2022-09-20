package sideproject.mercy.presentation.ui.interests.main.model

import sideproject.mercy.domain.entity.ActionEntity

sealed class InterestsMainActionEntity : ActionEntity() {
	object NextStep : InterestsMainActionEntity()
}