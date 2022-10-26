package sideproject.mercy.presentation.common.webview

import android.app.Activity
import android.webkit.JavascriptInterface
import android.webkit.WebView
import sideproject.mercy.utils.extensions.showToast

class CommonJavascriptInterface(
    private val activity: Activity,
    private val webView: WebView
) {
    @JavascriptInterface
    fun close() {
        activity.runOnUiThread { activity.finish() }
    }

    @JavascriptInterface
    fun showToast(message: String?) {
        message?.run {
            activity.runOnUiThread {
	            activity.showToast(this)
            }
        }
    }
}