package sideproject.mercy.startup

import android.content.Context
import androidx.startup.Initializer
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {

	override fun create(context: Context) {
		if (sideproject.mercy.BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}

	override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}