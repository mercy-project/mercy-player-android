package sideproject.mercy.shared.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceStorage @Inject constructor(context: Context) : BasePreference {
    private val prefs: Lazy<SharedPreferences> = lazy { // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    override fun put(key: String, value: String?) {
        prefs.value.edit {
            putString(key, value)
        }
    }

    override fun put(key: String, value: Boolean) {
        prefs.value.edit {
            putBoolean(key, value)
        }
    }

    override fun put(key: String, value: Int) {
        prefs.value.edit {
            putInt(key, value)
        }
    }

    override fun put(key: String, value: Long) {
        prefs.value.edit {
            putLong(key, value)
        }
    }

    fun putStringSet(key: String, value: Set<String>?) {
        prefs.value.edit {
            putStringSet(key, value)
        }
    }

    fun getStringSet(key: String): Set<String>? = prefs.value.getStringSet(key, null)

    override fun getValue(key: String, defValue: String?) = prefs.value.getString(key, defValue)
    override fun getValue(key: String, defValue: Int) = prefs.value.getInt(key, defValue)
    override fun getValue(key: String, defValue: Boolean) = prefs.value.getBoolean(key, defValue)
    override fun getValue(key: String, defValue: Long) = prefs.value.getLong(key, defValue)

    override fun remove(key: String) {
        prefs.value.edit {
            remove(key)
        }
    }

    fun clear() {
        prefs.value.edit {
            clear()
        }
    }

    companion object {
        const val PREFS_NAME = "sideproject.mercy.prefs"
    }
}