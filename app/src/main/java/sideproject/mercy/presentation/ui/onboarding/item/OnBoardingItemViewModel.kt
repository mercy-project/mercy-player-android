package sideproject.mercy.presentation.ui.onboarding.item

import android.graphics.drawable.Drawable
import sideproject.mercy.presentation.ClickEventNotifier
import sideproject.mercy.presentation.ui.onboarding.model.OnBoardingItemClickEntity

data class OnBoardingItemViewModel(
	val onBoardingTitle: String,
	val onBoardingSubTitle: String,
	val nextButtonName: String,
	val onBoardingDrawable: Drawable?,
	val eventNotifier: ClickEventNotifier
) {
	fun onClickNext() {
		eventNotifier.notifyClickEvent(OnBoardingItemClickEntity.NextItem)
	}
}