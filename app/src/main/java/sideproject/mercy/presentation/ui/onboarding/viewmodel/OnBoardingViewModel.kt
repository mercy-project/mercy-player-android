package sideproject.mercy.presentation.ui.onboarding.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.onboarding.model.OnBoardingActionEntity
import sideproject.mercy.shared.storage.PreferenceKeys
import sideproject.mercy.shared.storage.PreferenceStorage

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
	private val preferenceStorage: PreferenceStorage
): BaseViewModel() {

	fun saveFirstLaunched() {
		preferenceStorage.put(PreferenceKeys.PREF_KEY_FIRST_LAUNCH, false)
	}

	fun onClickNextPage() {
		notifyActionEvent(OnBoardingActionEntity.NextStep)
	}
}