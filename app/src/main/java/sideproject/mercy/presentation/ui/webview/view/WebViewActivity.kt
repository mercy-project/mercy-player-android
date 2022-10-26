package sideproject.mercy.presentation.ui.webview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivityWebViewBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.webview.event.WebViewActionEntity
import sideproject.mercy.presentation.ui.webview.viewmodel.WebViewViewModel
import sideproject.mercy.utils.dialog.alert.CommonDialog
import sideproject.mercy.utils.extensions.defaultFullSettings
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

	private val title: String by lazy { intent.getStringExtra(KEY_WEB_VIEW_TITLE).orEmpty() }
	private val url: String by lazy { intent.getStringExtra(KEY_WEB_VIEW_URL).orEmpty() }

	private lateinit var binding: ActivityWebViewBinding

	private val viewModel by viewModels<WebViewViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityWebViewBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@WebViewActivity
			viewModel = this@WebViewActivity.viewModel
		}

		setContentView(binding.root)

		if (url.isEmpty()) {
			CommonDialog(this)
				.message(getString(R.string.invalid_url))
				.leftButtonInfo(
					text = getString(R.string.confirm),
					onClickListener = { dialog, _ ->
						dialog.dismiss()
						finish()
					}
				)
			return
		}

		observeEventNotifier()

		viewModel.setToolbarTitle(title)

		binding.wvBase.defaultFullSettings(this@WebViewActivity)
		binding.wvBase.loadUrl(url)
	}

	private fun observeEventNotifier() {
		observeHandledEvent(viewModel.event.click) {
			handleSelectEvent(it)
		}
		observeHandledEvent(viewModel.event.action) {
			handleActionEvent(it)
		}
		observeHandledEvent(viewModel.event.throwable) {
			if (it.first is UnknownHostException) {
				showToast(getString(R.string.error_default))
			}
		}
	}

	private fun handleActionEvent(entity: ActionEntity) {
		when (entity) {
			is WebViewActionEntity.WebViewClose -> finish()
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
		}
	}

	companion object {

		const val KEY_WEB_VIEW_TITLE = "key_web_view_title"
		const val KEY_WEB_VIEW_URL = "key_web_view_url"

		fun startActivity(context: Context, title: String, url: String) {
			context.startActivity(
				Intent(context, WebViewActivity::class.java).apply {
					putExtra(KEY_WEB_VIEW_TITLE, title)
					putExtra(KEY_WEB_VIEW_URL, url)
				}
			)
		}
	}
}