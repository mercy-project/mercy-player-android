package sideproject.mercy.presentation.res

import androidx.annotation.StringRes

interface IStringResourceGetter {
    fun getStringRes(@StringRes id: Int): String
}
