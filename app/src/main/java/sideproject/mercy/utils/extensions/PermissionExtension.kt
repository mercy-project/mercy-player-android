package sideproject.mercy.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Context.isGrantedPermission(permission: String): Boolean {
	return ContextCompat.checkSelfPermission(
		this,
		permission
	) == PackageManager.PERMISSION_GRANTED
}

fun Activity.permissionRationalOr(permission: String, rationaleAction: () -> Unit, deniedAction: () -> Unit) {
	if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
		rationaleAction()
	} else {
		deniedAction()
	}
}