package sideproject.mercy.presentation.ui.account.signin.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.account.signin.model.SignInActionEntity

@HiltViewModel
class SignInViewModel @Inject constructor(
): BaseViewModel() {

	fun onClickTermsOfService() {
		notifyActionEvent(SignInActionEntity.TermsOfService)
	}

	fun onClickPrivacyPolicy() {
		notifyActionEvent(SignInActionEntity.PrivacyPolicy)
	}

	fun onClickSignInGoogle() {
		notifyActionEvent(SignInActionEntity.SignInGoogle)
	}
}