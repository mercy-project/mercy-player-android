package sideproject.mercy.presentation.ui.settings.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import sideproject.mercy.R

class SettingsMainFragment : PreferenceFragmentCompat() {

	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.root_preferences, rootKey)
	}
}