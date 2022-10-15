package sideproject.mercy.presentation.ui.onboarding.viewmodel

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import javax.inject.Inject
import sideproject.mercy.R
import sideproject.mercy.presentation.ClickEventNotifier
import sideproject.mercy.presentation.base.res.IDrawableResourceGetter
import sideproject.mercy.presentation.base.res.IStringResourceGetter
import sideproject.mercy.presentation.ui.onboarding.item.OnBoardingItemViewModel

class OnBoardingStringProvider @Inject constructor(
	private val context: Context
) : IStringResourceGetter, IDrawableResourceGetter {
	enum class Code {
		ON_BOARDING_NEXT,
		ON_BOARDING_COMPLETE,
		ON_BOARDING_TITLE_1,
		ON_BOARDING_TITLE_2,
		ON_BOARDING_TITLE_3,
		ON_BOARDING_SUBTITLE_1,
		ON_BOARDING_SUBTITLE_2,
		ON_BOARDING_SUBTITLE_3,
	}

	enum class DrawableCode {
		ON_BOARDING_DRAWABLE_1,
		ON_BOARDING_DRAWABLE_2,
		ON_BOARDING_DRAWABLE_3
	}

	fun getString(code: Code): String {
		return when (code) {
			Code.ON_BOARDING_NEXT -> getStringRes(R.string.next)
			Code.ON_BOARDING_COMPLETE -> getStringRes(R.string.do_start)
			Code.ON_BOARDING_TITLE_1 -> getStringRes(R.string.on_boarding_title1)
			Code.ON_BOARDING_TITLE_2 -> getStringRes(R.string.on_boarding_title2)
			Code.ON_BOARDING_TITLE_3 -> getStringRes(R.string.on_boarding_title3)
			Code.ON_BOARDING_SUBTITLE_1 -> getStringRes(R.string.on_boarding_subtitle1)
			Code.ON_BOARDING_SUBTITLE_2 -> getStringRes(R.string.on_boarding_subtitle2)
			Code.ON_BOARDING_SUBTITLE_3 -> getStringRes(R.string.on_boarding_subtitle3)
		}
	}

	override fun getStringRes(id: Int): String {
		return context.getString(id)
	}

	override fun getDrawableRes(id: Int): Drawable? {
		return AppCompatResources.getDrawable(context, id)
	}

	private fun getDrawable(code: DrawableCode): Drawable? {
		return when (code) {
			DrawableCode.ON_BOARDING_DRAWABLE_1 -> getDrawableRes(R.drawable.ic_favorite)
			DrawableCode.ON_BOARDING_DRAWABLE_2 -> getDrawableRes(R.drawable.ic_announcement)
			DrawableCode.ON_BOARDING_DRAWABLE_3 -> getDrawableRes(R.drawable.ic_book)
		}
	}

	fun getOnBoardingItemViewModels(
		eventNotifier: ClickEventNotifier
	): List<OnBoardingItemViewModel> {
		return listOf(
			OnBoardingItemViewModel(
				onBoardingTitle = getString(Code.ON_BOARDING_TITLE_1),
				onBoardingSubTitle = getString(Code.ON_BOARDING_SUBTITLE_1),
				nextButtonName = getString(Code.ON_BOARDING_NEXT),
				onBoardingDrawable = getDrawable(DrawableCode.ON_BOARDING_DRAWABLE_1),
				eventNotifier = eventNotifier
			),
			OnBoardingItemViewModel(
				onBoardingTitle = getString(Code.ON_BOARDING_TITLE_2),
				onBoardingSubTitle = getString(Code.ON_BOARDING_SUBTITLE_2),
				nextButtonName = getString(Code.ON_BOARDING_NEXT),
				onBoardingDrawable = getDrawable(DrawableCode.ON_BOARDING_DRAWABLE_2),
				eventNotifier = eventNotifier
			),
			OnBoardingItemViewModel(
				onBoardingTitle = getString(Code.ON_BOARDING_TITLE_3),
				onBoardingSubTitle = getString(Code.ON_BOARDING_SUBTITLE_3),
				nextButtonName = getString(Code.ON_BOARDING_COMPLETE),
				onBoardingDrawable = getDrawable(DrawableCode.ON_BOARDING_DRAWABLE_3),
				eventNotifier = eventNotifier
			)
		)
	}
}