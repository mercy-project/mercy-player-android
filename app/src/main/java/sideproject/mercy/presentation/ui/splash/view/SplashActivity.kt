package sideproject.mercy.presentation.ui.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import sideproject.mercy.databinding.ActivitySplashBinding
import sideproject.mercy.presentation.ui.main.view.MainActivity
import sideproject.mercy.presentation.ui.splash.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

	private lateinit var binding: ActivitySplashBinding
	private val viewModel by viewModels<SplashViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivitySplashBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@SplashActivity
			viewModel = this@SplashActivity.viewModel
		}

		setContentView(binding.root)

		lifecycleScope.launchWhenResumed {
			delay(2_000)
			moveToNextActivity()
		}
	}

	private fun moveToNextActivity() {
		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
		finish()
	}
}