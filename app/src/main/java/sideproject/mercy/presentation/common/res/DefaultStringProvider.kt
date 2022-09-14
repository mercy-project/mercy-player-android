package sideproject.mercy.presentation.common.res

import android.content.Context
import sideproject.mercy.R
import javax.inject.Inject
import sideproject.mercy.presentation.base.res.IStringResourceGetter
import sideproject.mercy.presentation.common.res.DefaultStringProvider.Code.ERROR_DEFAULT

class DefaultStringProvider @Inject constructor(private val context: Context):
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