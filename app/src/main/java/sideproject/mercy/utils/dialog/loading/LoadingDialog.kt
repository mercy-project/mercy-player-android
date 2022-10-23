package sideproject.mercy.utils.dialog.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import sideproject.mercy.R

object LoadingDialog {

    fun showProgressDialog(context: Context, cancelable: Boolean): Dialog? {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.view_loading_transparent)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(cancelable)
        dialog.show()
        return dialog
    }
}