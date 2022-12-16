package sideproject.mercy.presentation.ui.survey.complete.viewmodel

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.core.text.inSpans
import javax.inject.Inject
import sideproject.mercy.R
import sideproject.mercy.R.color
import sideproject.mercy.presentation.base.res.IStringResourceGetter

class SurveyCompleteStringProvider @Inject constructor(
	private val context: Context
): IStringResourceGetter {

	enum class Code {
		WELCOME,
		WELCOME_NAME_TO
	}

	fun getString(code: Code): String {
		return when (code) {
			Code.WELCOME -> getStringRes(R.string.welcome)
			Code.WELCOME_NAME_TO -> getStringRes(R.string.name_to)
		}
	}

	fun getWelcomeUser(userName: String): SpannableStringBuilder {
		val userNameTo = getString(Code.WELCOME_NAME_TO)
		val welcome = getString(Code.WELCOME)

		return SpannableStringBuilder().apply {
			inSpans(
				ForegroundColorSpan(ContextCompat.getColor(context, color.color_00c880))
			) {
				append(userName)
			}
			bold { append(userNameTo) }
			appendLine()
			bold {
				append(welcome)
			}
		}
	}

	override fun getStringRes(id: Int): String {
		return context.getString(id)
	}
}