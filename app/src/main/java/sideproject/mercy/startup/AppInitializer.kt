package sideproject.mercy.startup

import android.content.Context
import androidx.startup.Initializer
import sideproject.mercy.shared.authentication.manager.UserInfoManager

class AppInitializer : Initializer<Unit> {

	override fun create(context: Context) {
		UserInfoManager.init(context)
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}