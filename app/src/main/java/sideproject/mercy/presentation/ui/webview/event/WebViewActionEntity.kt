package sideproject.mercy.presentation.ui.webview.event

import sideproject.mercy.domain.entity.ActionEntity

sealed class WebViewActionEntity : ActionEntity() {
	object WebViewClose : WebViewActionEntity()
}