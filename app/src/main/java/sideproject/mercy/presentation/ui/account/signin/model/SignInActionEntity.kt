package sideproject.mercy.presentation.ui.account.signin.model

import sideproject.mercy.domain.entity.ActionEntity

sealed class SignInActionEntity : ActionEntity() {
	object SignInGoogle : SignInActionEntity()
}