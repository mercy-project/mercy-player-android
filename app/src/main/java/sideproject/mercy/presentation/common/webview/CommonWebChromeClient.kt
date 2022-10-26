package sideproject.mercy.presentation.common.webview

import android.content.Context
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import sideproject.mercy.R
import sideproject.mercy.utils.dialog.alert.CommonDialog

class CommonWebChromeClient(
    private val context: Context
) : WebChromeClient() {

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {

        message?.let {
            CommonDialog(context)
                .message(it)
                .leftButtonInfo(
                    text = context.getString(R.string.confirm),
                    onClickListener = { dialog, _ ->
                        result?.confirm()
                        dialog.dismiss()
                    }
                )
                .buildOneButton()
                .show()

            return true
        }

        return super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {

        message?.let {
            CommonDialog(context)
                .message(it)
                .leftButtonInfo(
                    colorId = R.color.gray_333,
                    text = context.getString(R.string.confirm),
                    onClickListener = { dialog, _ ->
                        result?.confirm()
                        dialog.dismiss()
                    }
                )
                .rightButtonInfo(
                    colorId = R.color.primary,
                    text = context.getString(R.string.cancel),
                    onClickListener = { dialog, _ ->
                        result?.cancel()
                        dialog.dismiss()
                    }
                )
                .buildTwoButton()
                .show()

            return true
        }

        return super.onJsConfirm(view, url, message, result)
    }
}