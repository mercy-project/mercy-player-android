package sideproject.mercy.presentation.ui.search.main.viewmodel

import android.content.Context
import sideproject.mercy.R
import sideproject.mercy.presentation.base.res.IStringResourceGetter
import sideproject.mercy.presentation.ui.search.main.viewmodel.SearchMainStringProvider.Code.ERROR_DEFAULT
import javax.inject.Inject

class SearchMainStringProvider @Inject constructor(private val context: Context):
    IStringResourceGetter {

    enum class Code {
        ERROR_DEFAULT,
    }

    fun getString(code: Code): String {
        return when (code) {
            ERROR_DEFAULT -> getStringRes(R.string.error_default)
        }
    }

    override fun getStringRes(id: Int): String {
        return context.getString(id)
    }
}