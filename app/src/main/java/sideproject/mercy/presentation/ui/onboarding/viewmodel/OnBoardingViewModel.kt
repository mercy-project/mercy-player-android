package sideproject.mercy.presentation.ui.onboarding.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.onboarding.item.OnBoardingItemViewModel
import sideproject.mercy.presentation.ui.onboarding.model.OnBoardingActionEntity
import sideproject.mercy.shared.storage.PreferenceKeys
import sideproject.mercy.shared.storage.PreferenceStorage

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
	private val preferenceStorage: PreferenceStorage,
	private val stringProvider: OnBoardingStringProvider
): BaseViewModel() {

	private val onBoardingList: MutableList<OnBoardingItemViewModel> = mutableListOf()

	fun saveFirstLaunched() {
		preferenceStorage.put(PreferenceKeys.PREF_KEY_FIRST_LAUNCH, false)
	}

	fun generateOnBoardingData() {
		onBoardingList.clear()
		onBoardingList.addAll(stringProvider.getOnBoardingItemViewModels(this))

		notifyActionEvent(OnBoardingActionEntity.UpdateList(onBoardingList))
	}
}