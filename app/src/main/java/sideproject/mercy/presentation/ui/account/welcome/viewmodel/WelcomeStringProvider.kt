package sideproject.mercy.presentation.ui.account.welcome.viewmodel

import android.content.Context
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import javax.inject.Inject
import sideproject.mercy.R
import sideproject.mercy.presentation.base.res.IStringResourceGetter

class WelcomeStringProvider @Inject constructor(
	private val context: Context
): IStringResourceGetter {

	enum class Code {
		WELCOME_HELLO,
		WELCOME_EXPLAIN
	}

	fun getString(code: Code): String {
		return when (code) {
			Code.WELCOME_HELLO -> getStringRes(R.string.welcome_hello)
			Code.WELCOME_EXPLAIN -> getStringRes(R.string.welcome_need_information)
		}
	}

	fun getWelcomeText(): SpannableStringBuilder {
		val hello = getString(Code.WELCOME_HELLO)
		val explain = getString(Code.WELCOME_EXPLAIN)

		return SpannableStringBuilder().apply {
			append(hello)
			appendLine()
			bold {
				append(explain)
			}
		}
	}

	override fun getStringRes(id: Int): String {
		return context.getString(id)
	}
}