package sideproject.mercy.presentation.ui.survey.complete.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.survey.complete.model.SurveyCompleteActionEntity
import sideproject.mercy.shared.authentication.manager.UserInfoManager

@HiltViewModel
class SurveyCompleteViewModel @Inject constructor(
	stringProvider: SurveyCompleteStringProvider
) : BaseViewModel() {

	val viewState = SurveyCompleteViewState()

	init {
		viewState.welcomeUser.set(
			stringProvider.getWelcomeUser(
				UserInfoManager.getUserName().orEmpty()
			)
		)
	}

	fun clickConfirm() {
		notifyActionEvent(SurveyCompleteActionEntity.MoveToMain)
	}
}