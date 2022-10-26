package sideproject.mercy.presentation.common.webview

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import sideproject.mercy.R
import sideproject.mercy.shared.log.L
import sideproject.mercy.utils.dialog.alert.CommonDialog
import sideproject.mercy.utils.dialog.loading.LoadingDialog

class CommonWebViewClient(
	private val activity: Activity
) : WebViewClient() {

	var dialog: Dialog? = null

	@TargetApi(Build.VERSION_CODES.N)
	override fun shouldOverrideUrlLoading(
		view: WebView?,
		request: WebResourceRequest?
	): Boolean {
		val url = request?.url.toString()
		val replaceUrl = if (url.startsWith("http:")) {
			url.replace("http:", "https:")
		} else url
		return shouldOverrideUrl(view, replaceUrl, request)
	}

	@Deprecated("Deprecated in Java")
	override fun shouldOverrideUrlLoading(
		view: WebView?,
		url: String?
	): Boolean {
		val replaceUrl = if (url?.startsWith("http:") == true) {
			url.replace("http:", "https:")
		} else url
		return shouldOverrideUrl(view, replaceUrl, null)
	}

	private fun isContainsUriIntentScheme(url: String): Boolean {
		return !url.contains("intent") &&
			(url.contains("market://") ||
				url.endsWith(".apk"))
	}

	private fun shouldOverrideUrl(
		view: WebView?,
		url: String?,
		request: WebResourceRequest?
	): Boolean {
		try {

			if (url?.isNotEmpty() == true) {
				if (isContainsUriIntentScheme(url)) {
					// Todo: 마켓 이동
				} else {
					if (url.contains("http://goto.kakao.com/") ||
						url.contains("intent") ||
						url.contains("bit.ly")
					) {
						// 외부 연결
						val uri = Uri.parse(url)
						val intent = Intent(Intent.ACTION_VIEW, uri)
						intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
						activity.startActivity(intent)
						return true
					} else if (url.startsWith("tel:")) {
						val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
						activity.startActivity(intent)
						return true
					}
				}
			}
		} catch (e: Exception) {
			// do nothing
		}
		return true
	}

	override fun onPageFinished(
		view: WebView?,
		url: String?
	) {
		super.onPageFinished(view, url)
		dismissProgressDialog()
	}

	override fun onPageStarted(
		view: WebView?,
		url: String?,
		favicon: Bitmap?
	) {
		super.onPageStarted(view, url, favicon)
		showProgressDialog()
	}

	@SuppressLint("WebViewClientOnReceivedSslError")
	override fun onReceivedSslError(
		view: WebView?,
		handler: SslErrorHandler?,
		error: SslError?
	) {
		super.onReceivedSslError(view, handler, error)
		try {
			var message = view?.url.orEmpty()
			message += when (error?.primaryError) {
				SslError.SSL_EXPIRED -> "[${SslError.SSL_EXPIRED}]이 사이트의 보안 인증서는 신뢰할 수 없습니다."
				SslError.SSL_IDMISMATCH -> "[${SslError.SSL_IDMISMATCH}]이 사이트의 보안 인증서는 신뢰할 수 없습니다."
				SslError.SSL_NOTYETVALID -> "[${SslError.SSL_NOTYETVALID}]이 사이트의 보안 인증서는 신뢰할 수 없습니다."
				SslError.SSL_UNTRUSTED -> "[${SslError.SSL_UNTRUSTED}]이 사이트의 보안 인증서는 신뢰할 수 없습니다."
				else -> activity.resources.getString(R.string.ssl_error_description)
			}

			CommonDialog(activity)
				.message(message)
				.leftButtonInfo(
					text = activity.getString(R.string.ignore),
					onClickListener = { dialog, _ ->
						handler?.proceed()
						dialog.dismiss()
					}
				)
				.rightButtonInfo(
					text = activity.getString(R.string.cancel),
					onClickListener = { dialog, _ ->
						handler?.cancel()
						dialog.dismiss()
					}
				)
		} catch (e: Exception) {
			L.e(e)
		}
	}

	@Suppress("DEPRECATION")
	override fun onReceivedError(
		view: WebView?,
		errorCode: Int,
		description: String?,
		failingUrl: String?
	) {
		super.onReceivedError(view, errorCode, description, failingUrl)
		dismissProgressDialog()
	}

	private fun dismissProgressDialog() {
		if (activity.isFinishing.not()) {
			dialog?.dismiss()
			dialog = null
		}
	}

	private fun showProgressDialog() {
		if (activity.isFinishing.not()) {
			dialog?.dismiss()
			dialog = LoadingDialog.showProgressDialog(activity, true)
		}
	}
}