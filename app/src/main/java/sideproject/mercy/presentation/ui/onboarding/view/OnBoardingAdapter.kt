package sideproject.mercy.presentation.ui.onboarding.view

import sideproject.mercy.R
import sideproject.mercy.presentation.base.adapter.ItemDiffCallback
import sideproject.mercy.presentation.base.adapter.SimpleListBindingAdapter
import sideproject.mercy.presentation.ui.onboarding.item.OnBoardingItemViewModel

class OnBoardingAdapter : SimpleListBindingAdapter<OnBoardingItemViewModel>(
	ItemDiffCallback(
		onItemsTheSame = { old, new ->
			old.onBoardingTitle == new.onBoardingTitle &&
				old.onBoardingSubTitle == new.onBoardingSubTitle &&
				old.onBoardingDrawable == new.onBoardingDrawable
		},
		onContentsTheSame = { old, new -> old == new }
	))
{
	override fun getItemViewType(position: Int): Int {
		return R.layout.layout_on_boarding
	}
}