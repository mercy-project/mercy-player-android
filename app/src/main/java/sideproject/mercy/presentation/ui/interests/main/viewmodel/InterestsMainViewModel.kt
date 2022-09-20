package sideproject.mercy.presentation.ui.interests.main.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.presentation.ui.interests.main.model.InterestsMainActionEntity

@HiltViewModel
class InterestsMainViewModel @Inject constructor(
): BaseViewModel() {

	fun onClickNext() {
		notifyActionEvent(InterestsMainActionEntity.NextStep)
	}
}