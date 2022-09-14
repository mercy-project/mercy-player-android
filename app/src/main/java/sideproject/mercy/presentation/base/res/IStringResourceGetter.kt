package sideproject.mercy.presentation.base.res

import androidx.annotation.StringRes

interface IStringResourceGetter {
    fun getStringRes(@StringRes id: Int): String
}
