package sideproject.mercy.presentation.ui.webview.viewmodel

import sideproject.mercy.presentation.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sideproject.mercy.presentation.ui.webview.event.WebViewActionEntity

@HiltViewModel
class WebViewViewModel @Inject constructor(

) : BaseViewModel() {
	val viewState = WebViewViewState()

	fun setToolbarTitle(title: String) {
		viewState.title.set(title)
	}

	fun onClickClose() {
		notifyActionEvent(WebViewActionEntity.WebViewClose)
	}
}