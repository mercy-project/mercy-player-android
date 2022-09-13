package sideproject.mercy.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes

fun ViewGroup.createView(@LayoutRes layoutId: Int): View =
	LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.hideKeyboard(): Boolean {
	val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	return imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showKeyboard() {
	postDelayed({
		val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.showSoftInput(
			this,
			InputMethodManager.SHOW_IMPLICIT
		)
	}, 300)
}

/**
 * Focus 를 잃으면 키보드 숨기는 OnFocusChangeListener 를 반환
 */
fun getAutoHideKeyboardFocusChangeListener() = View.OnFocusChangeListener { view, hasFocus ->
	if (!hasFocus) {
		view.hideKeyboard()
	}
}