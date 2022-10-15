package sideproject.mercy.presentation.ui.onboarding.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivityOnBoardingBinding
import sideproject.mercy.domain.entity.ActionEntity
import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.presentation.ui.main.view.MainActivity
import sideproject.mercy.presentation.ui.onboarding.model.OnBoardingActionEntity
import sideproject.mercy.presentation.ui.onboarding.model.OnBoardingItemClickEntity
import sideproject.mercy.presentation.ui.onboarding.viewmodel.OnBoardingViewModel
import sideproject.mercy.utils.extensions.observeHandledEvent
import sideproject.mercy.utils.extensions.showToast

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

	private lateinit var binding: ActivityOnBoardingBinding
	private val viewModel: OnBoardingViewModel by viewModels()

	private val adapter = OnBoardingAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityOnBoardingBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@OnBoardingActivity
			viewModel = this@OnBoardingActivity.viewModel

			vpOnBoarding.apply {
				adapter = this@OnBoardingActivity.adapter
				isUserInputEnabled = false
			}

			dotIndicator.attachTo(vpOnBoarding)
		}

		setContentView(binding.root)

		observeEventNotifier()

		viewModel.generateOnBoardingData()
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
			is OnBoardingActionEntity.UpdateList -> {
				adapter.clear()
				adapter.addItems(entity.list)
			}

			is OnBoardingActionEntity.NextStep -> {
				// Todo - On.1.0.0.0: ViewPage 연결 후 페이지 변경 적용
				moveToNextActivity()
			}
		}
	}

	private fun handleSelectEvent(entity: ClickEntity) {
		when (entity) {
			is OnBoardingItemClickEntity.NextItem -> {
				moveToNextOnBoarding()
			}
		}
	}

	private fun moveToNextOnBoarding() {
		val viewPagerPosition = binding.vpOnBoarding.currentItem
		if (viewPagerPosition == adapter.itemCount -1) {
			moveToNextActivity()
		} else {
			binding.vpOnBoarding.currentItem = viewPagerPosition + 1
		}
	}

	private fun moveToNextActivity() {

		// Todo - On.1.0.0.0: 최초 진입 플래그 저장 기준에 따라 변경 가능성 있음
		viewModel.saveFirstLaunched()

		val intent = Intent(this, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
		}

		startActivity(intent)
		finish()
	}
}