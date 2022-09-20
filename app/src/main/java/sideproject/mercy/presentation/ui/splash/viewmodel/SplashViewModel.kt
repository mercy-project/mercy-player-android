package sideproject.mercy.presentation.ui.splash.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import sideproject.mercy.shared.storage.PreferenceKeys
import sideproject.mercy.shared.storage.PreferenceStorage

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val preferenceStorage: PreferenceStorage
): BaseViewModel() {
	/**
	 * 고려해야할 작업
	 *
	 * - 자동 로그인, 인증 정보 확인
	 * - 버전 정보 확인
	 */

	fun isFirstLaunch() = preferenceStorage.getValue(PreferenceKeys.PREF_KEY_FIRST_LAUNCH, true)
}