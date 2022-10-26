package sideproject.mercy.utils.dialog.alert

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import sideproject.mercy.utils.dialog.alert.AlertButtonInfo.Companion

class CommonDialog(private val context: Context) {

    private var title: String = ""
    private var message: String = ""
    private var leftButtonInfo: AlertButtonInfo =
	    Companion.createEmptyAlertButton()
    private var rightButtonInfo: AlertButtonInfo =
	    Companion.createEmptyAlertButton()
    private var cancelable: Boolean = true

    fun title(title: String): CommonDialog {
        this.title = title
        return this
    }

    fun message(message: String): CommonDialog {
        this.message = message
        return this
    }

    fun leftButtonInfo(
        @ColorRes colorId: Int? = null,
        text: String,
        onClickListener: DialogInterface.OnClickListener =
            DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
    ): CommonDialog {
        this.leftButtonInfo = generateAlertButtonInfo(
            colorId = colorId,
            text = text,
            onClickListener = onClickListener
        )
        return this
    }

    fun rightButtonInfo(
        @ColorRes colorId: Int? = null,
        text: String,
        onClickListener: DialogInterface.OnClickListener =
            DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
    ): CommonDialog {
        this.rightButtonInfo = generateAlertButtonInfo(
            colorId = colorId,
            text = text,
            onClickListener = onClickListener
        )
        return this
    }

    private fun generateAlertButtonInfo(
        colorId: Int?,
        text: String,
        onClickListener: DialogInterface.OnClickListener
    ): AlertButtonInfo {
        return if (colorId == null) {
	        Companion.createDefaultAlertButton(
		        text = text,
		        onClickListener = onClickListener
	        )
        } else {
	        Companion.createAlertButton(
		        context = context,
		        colorId = colorId,
		        text = text,
		        onClickListener = onClickListener
	        )
        }
    }

    fun cancelable(cancelable: Boolean): CommonDialog {
        this.cancelable = cancelable
        return this
    }

    /**
     * Left, Right ButtonInfo 중에 text 가 있는 버튼 사용
     */
    fun buildOneButton(): AlertDialog {

        val buttonInfo = when {
            leftButtonInfo.text.isEmpty() && rightButtonInfo.text.isNotEmpty() -> {
                rightButtonInfo
            }
            else -> leftButtonInfo
        }

        return AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setCancelable(cancelable)
            setPositiveButton(buttonInfo.text, buttonInfo.onClickListener)
        }.create()
    }

    fun buildTwoButton(): AlertDialog {
        return AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setCancelable(cancelable)
            setNegativeButton(leftButtonInfo.text, leftButtonInfo.onClickListener)
            setPositiveButton(rightButtonInfo.text, rightButtonInfo.onClickListener)
        }.create()
    }
}