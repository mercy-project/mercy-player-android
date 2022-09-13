package sideproject.mercy.presentation.base.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionCheckActivity : AppCompatActivity() {

    private val requestPermissions by lazy { intent.extras?.getStringArray(REQUEST_PERMISSIONS).orEmpty() }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)

        checkPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val deniedPermission = mutableListOf<String>()

	    permissions.forEachIndexed { index, permission ->
		    if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
			    deniedPermission.add(permission)
		    }
	    }

        if (deniedPermission.isEmpty()) {
            setResult(PERMISSION_RESULT_SUCCESS)
            finish()
        } else {
            setResult(PERMISSION_RESULT_FAIL)
            finish()
        }
    }

    private fun checkPermission() {
	    if (requestPermissions.isEmpty())
			return

        val deniedPermissions = mutableListOf<String>()

        requestPermissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(
                    this@PermissionCheckActivity,
		            permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                deniedPermissions.add(permission)
            }
        }

        if (deniedPermissions.isEmpty()) {
            setResult(PERMISSION_RESULT_SUCCESS)
            finish()
        } else {
            ActivityCompat.requestPermissions(
                this@PermissionCheckActivity,
                requestPermissions,
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

	companion object {
		const val PERMISSION_REQUEST_CODE = 1001
		const val PERMISSION_RESULT_SUCCESS = 1002
		const val PERMISSION_RESULT_FAIL = 1003

		private const val REQUEST_PERMISSIONS = "REQUEST_PERMISSIONS"

		fun generateIntent(context: Context, requestPermissions: Array<String>): Intent {
			return Intent(context, PermissionCheckActivity::class.java).apply {
				putExtra(REQUEST_PERMISSIONS, requestPermissions)
			}
		}
	}
}