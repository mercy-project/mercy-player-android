package sideproject.mercy.presentation.ui.account.welcome.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.account.welcome.model.WelcomeActionEntity

@HiltViewModel
class WelcomeViewModel @Inject constructor(
	stringProvider: WelcomeStringProvider
) : BaseViewModel() {

	val viewState = WelcomeViewState()

	init {
		viewState.welcomeText.set(stringProvider.getWelcomeText())
	}

	fun clickConfirm() {
		notifyActionEvent(WelcomeActionEntity.MoveToSurvey)
	}
}