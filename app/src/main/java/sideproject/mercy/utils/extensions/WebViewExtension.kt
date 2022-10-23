package sideproject.mercy.utils.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import sideproject.mercy.presentation.common.webview.CommonJavascriptInterface
import sideproject.mercy.presentation.common.webview.CommonWebChromeClient
import sideproject.mercy.presentation.common.webview.CommonWebViewClient

@SuppressLint("SetJavaScriptEnabled")
fun WebView.setDefaultSettings() {
	settings.apply {
		cacheMode = WebSettings.LOAD_NO_CACHE
		setSupportMultipleWindows(false)
		javaScriptEnabled = true
		javaScriptCanOpenWindowsAutomatically = true
		loadWithOverviewMode = true
		useWideViewPort = true
		domStorageEnabled = true
		mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

		val cookieManager = CookieManager.getInstance()
		cookieManager.setAcceptCookie(true)
		cookieManager.setAcceptThirdPartyCookies(this@setDefaultSettings, true)
	}
}

fun WebView.addCommonJavascriptInterface(activity: Activity) {
	addJavascriptInterface(CommonJavascriptInterface(activity, this), "android")
}

fun WebView.addCommonWebViewClient(activity: Activity) {
	webViewClient = CommonWebViewClient(activity)
}

fun WebView.addCommonWebChromeClient() {
	webChromeClient = CommonWebChromeClient(context)
}

fun WebView.defaultFullSettings(activity: Activity) {
	addCommonWebViewClient(activity)
	addCommonWebChromeClient()
	setDefaultSettings()
}