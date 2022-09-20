package sideproject.mercy.presentation.ui.splash.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel

@HiltViewModel
class SplashViewModel @Inject constructor(

): BaseViewModel() {
	/**
	 * 고려해야할 작업
	 *
	 * - 자동 로그인, 인증 정보 확인
	 * - 버전 정보 확인
	 */
}