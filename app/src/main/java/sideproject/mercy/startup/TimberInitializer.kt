package sideproject.mercy.startup

import android.content.Context
import androidx.startup.Initializer
import sideproject.mercy.shared.log.L

class TimberInitializer : Initializer<Unit> {

	override fun create(context: Context) {
		L.init()
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}