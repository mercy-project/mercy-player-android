package sideproject.mercy.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Insets
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import android.util.Size
import android.view.WindowInsets
import android.view.WindowManager
import android.view.WindowMetrics
import android.widget.Toast
import androidx.core.os.bundleOf

fun Context.getRealDisplaySize(): Size {
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

		val metrics: WindowMetrics =
			getSystemService(WindowManager::class.java).currentWindowMetrics

		val windowInsets: WindowInsets = metrics.windowInsets
		val insets: Insets = windowInsets.getInsetsIgnoringVisibility(
			WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
		)

		val insetsWidth: Int = insets.right + insets.left
		val insetsHeight: Int = insets.top + insets.bottom

		val bounds: Rect = metrics.bounds

		return Size(
			bounds.width() - insetsWidth,
			bounds.height() - insetsHeight
		)
	} else {
		val displayMetrics = DisplayMetrics()
		(this as? Activity)?.windowManager?.defaultDisplay?.getRealMetrics(displayMetrics)

		return Size(
			displayMetrics.widthPixels,
			displayMetrics.heightPixels - getStatusBarHeight() - getNavigationBarHeight()
		)
	}
}

fun Context.getActionBarSize(): Int {
	val styledAttributes = theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))

	try {
		return styledAttributes.getDimension(0, 0f).toInt()
	} finally {
		styledAttributes.recycle()
	}
}

fun Context.getStatusBarHeight(): Int {
	var statusBar = 0
	val statusBarId = resources.getIdentifier("status_bar_height", "dimen", "android")
	if (statusBarId > 0) {
		statusBar = resources.getDimensionPixelSize(statusBarId)
	}
	return statusBar
}

fun Context.getNavigationBarHeight(): Int {
	var navigationBarHeight = 0
	val resourceIdBottom: Int =
		resources.getIdentifier("navigation_bar_height", "dimen", "android")
	if (resourceIdBottom > 0) {
		navigationBarHeight = resources.getDimensionPixelSize(resourceIdBottom)
	}

	return navigationBarHeight
}

inline fun <reified T : Activity> Context.buildIntent(
    vararg argument: Pair<String, Any?>
): Intent = Intent(this, T::class.java).apply {
    putExtras(bundleOf(*argument))
}

inline fun <reified T: Activity> Context.startActivity(
    vararg argument: Pair<String, Any?>
) {
    startActivity(buildIntent<T>(*argument))
}

fun Activity.openExternalLink(url: String) {
	val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
	startActivity(intent)
}

fun Int.dp2px() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Context.showToast(message: String) { 
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.parseUriPackageName() = Uri.parse("package:${packageName}") ?: null