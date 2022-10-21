package sideproject.mercy.presentation.base.res

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

interface IDrawableResourceGetter {
    fun getDrawableRes(@DrawableRes id: Int): Drawable?
}

