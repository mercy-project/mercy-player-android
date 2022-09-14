package sideproject.mercy.presentation.ui.main.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import sideproject.mercy.R
import sideproject.mercy.databinding.ActivityMainBinding
import sideproject.mercy.presentation.base.viewmodel.NetworkHandler
import sideproject.mercy.presentation.base.viewmodel.NetworkHandlerImpl
import sideproject.mercy.presentation.common.activity.PermissionCheckActivity
import sideproject.mercy.presentation.ui.main.viewmodel.MainViewModel
import sideproject.mercy.utils.extensions.isGrantedPermission
import sideproject.mercy.utils.extensions.parseUriPackageName
import sideproject.mercy.utils.extensions.permissionRationalOr
import sideproject.mercy.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetworkHandler by NetworkHandlerImpl() {

	private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

	private lateinit var navController: NavController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater).apply {
			lifecycleOwner = this@MainActivity
			viewModel = this@MainActivity.viewModel
		}

		setContentView(binding.root)

		setNetworkHandler(this, binding.root, this)

		if (savedInstanceState == null) {
			setUpNavigation()
		}

		initObserve()
		initData()
	}

	private fun setUpNavigation() {
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
		navController = navHostFragment.navController

		binding.bnvMain.run {
			setupWithNavController(navController)
			setOnItemSelectedListener {
				// menu id에 해당하는 탭으로 이동
				navigateBottomMenu(it.itemId)
				true
			}
		}
	}

	private fun navigateBottomMenu(destinationId: Int, args: Bundle? = null) {
		navController.navigate(destinationId, args)
	}
	
	private fun initObserve() {
		viewModel.contributors.observe(this) {
			Timber.d("contributors: $it")
		}
	}

	private fun initData() {
        viewModel.getContributors()

		moveToContactPermissionTest()
    }

	private fun moveToContactPermissionTest() {
		activityResultLauncher.launch(
			PermissionCheckActivity.generateIntent(
				context = this,
				requestPermissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
			)
		)
	}

	private val activityResultLauncher = registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) { result: ActivityResult ->
		when (result.resultCode) {
			// 권한이 허용되어있으면 항상 호출
			PermissionCheckActivity.PERMISSION_RESULT_SUCCESS -> {
				Timber.d("activityResultLauncher - ${Manifest.permission.READ_EXTERNAL_STORAGE} is Granted")
			}
			PermissionCheckActivity.PERMISSION_RESULT_FAIL -> {
				permissionRationalOr(
					permission = Manifest.permission.READ_EXTERNAL_STORAGE,
					rationaleAction = {
						Toast.makeText(
							this,
							getString(R.string.denied_external_storage_message),
							Toast.LENGTH_SHORT
						).show()
					},
					deniedAction = {
						moveToSetting()
					}
				)
			}
		}
	}

	private val settingResultLauncher = registerForActivityResult(
		ActivityResultContracts.StartActivityForResult()
	) {
		if (isGrantedPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
			Timber.d("settingResultLauncher - ${Manifest.permission.READ_EXTERNAL_STORAGE} is Granted")
		}
	}

	private fun moveToSetting() {
		AlertDialog.Builder(this).run {
			setTitle(R.string.denied_external_storage_title)
			setMessage(R.string.move_to_setting_permission)
			setPositiveButton(
				R.string.do_setting
			) { _, _ ->
				parseUriPackageName()?.let { packageNameUri ->
					val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
					intent.data = packageNameUri
					settingResultLauncher.launch(intent)
				}
			}
			setNegativeButton(
				R.string.cancel
			) { _, _ -> }
			show()
		}
	}


	private val mainId = R.id.menu_home
	private var backPressedAt: Long = 0

	override fun onBackPressed() {
		navController.run {
			if (currentDestination != null && currentDestination?.id != mainId) {
				binding.bnvMain.selectedItemId = graph.startDestinationId

				return
			}
		}

		if (backPressedAt + TimeUnit.SECONDS.toMillis(2) > System.currentTimeMillis()) {
			finishAffinity()
		} else {
			showToast(resources.getString(R.string.app_finish))
			backPressedAt = System.currentTimeMillis()
		}
	}
}