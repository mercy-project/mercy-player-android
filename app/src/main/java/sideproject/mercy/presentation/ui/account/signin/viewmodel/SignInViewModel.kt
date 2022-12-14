package sideproject.mercy.presentation.ui.account.signin.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.account.signin.model.SignInActionEntity

@HiltViewModel
class SignInViewModel @Inject constructor(
): BaseViewModel() {

	/**
	 * 웰컴 페이지로 이동할지 설문조사로 이동할지 분기
	 */
	fun checkNextStep() {
		// Todo: preference 에서 step 을 저장 후 분기 (firebase token + boolean)
		notifyActionEvent(SignInActionEntity.MoveToWelcome)
		// notifyActionEvent(SignInActionEntity.MoveToSurvey)
	}

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