package sideproject.mercy.utils.dialog.alert

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import sideproject.mercy.R
import sideproject.mercy.R.color

class AlertButtonInfo(
    val text: CharSequence,
    val onClickListener: DialogInterface.OnClickListener
) {
    companion object {
        fun createAlertButton(
            context: Context,
            @ColorRes colorId: Int? = null,
            text: String,
            onClickListener: DialogInterface.OnClickListener =
                DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
        ): AlertButtonInfo {
            return AlertButtonInfo(
                text = buildSpannedString {
                    val applyColor = colorId?.let { realColor ->
                        ContextCompat.getColor(context, realColor)
                    } ?: ContextCompat.getColor(context, color.gray_333)

                    color(applyColor) {
                        append(text)
                    }
                },
                onClickListener = onClickListener
            )
        }

        /**
         * 색상을 지정하지 않으면 기본 primaryColor 로 지정
         */
        fun createDefaultAlertButton(
            text: String,
            onClickListener: DialogInterface.OnClickListener =
                DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss() }
        ): AlertButtonInfo {
            return AlertButtonInfo(
                text = text,
                onClickListener = onClickListener
            )
        }

        fun createEmptyAlertButton(): AlertButtonInfo {
            return AlertButtonInfo(
                text = "",
                onClickListener = { dialog, _ -> dialog.dismiss() }
            )
        }
    }
}