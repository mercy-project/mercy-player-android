package sideproject.mercy.presentation.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.View
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import sideproject.mercy.R
import sideproject.mercy.shared.log.L

interface NetworkHandler {
	fun setNetworkHandler(
		context: Context,
		rootView: View,
		lifecycleOwner: LifecycleOwner
	)
}

class NetworkHandlerImpl : NetworkHandler, LifecycleEventObserver {

	private var context: Context? = null
	private var rootView: View? = null
	private var lifecycleOwner: LifecycleOwner? = null

	override fun setNetworkHandler(context: Context, rootView: View, lifecycleOwner: LifecycleOwner) {
		this.context = context
		this.rootView = rootView
		this.lifecycleOwner = lifecycleOwner

		lifecycleOwner.lifecycle.addObserver(this)
	}

	private val connectivityManager by lazy {
		context?.getSystemService(ConnectivityManager::class.java)
	}

	private val internetConnectionSnackbar: Snackbar? by lazy {
		rootView?.run {
			Snackbar.make(this, context.resources.getString(R.string.error_default), Snackbar.LENGTH_INDEFINITE)
		}
	}


	private val networkCallback = object : ConnectivityManager.NetworkCallback() {
		override fun onAvailable(network : Network) {
			internetConnectionSnackbar?.let { snackbar ->
				if (snackbar.isShown) {
					snackbar.dismiss()
				}
			}
		}

		override fun onLost(network : Network) {
			internetConnectionSnackbar?.show()
		}

		override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
		}

		override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
		}
	}

	private fun registerNetworkCallback() {
		if (VERSION.SDK_INT >= VERSION_CODES.N) {
			connectivityManager?.registerDefaultNetworkCallback(networkCallback)
		}
	}

	private fun unRegisterNetworkCallback() {
		if (VERSION.SDK_INT >= VERSION_CODES.N) {
			connectivityManager?.unregisterNetworkCallback(networkCallback)
		}
	}

	override fun onStateChanged(source: LifecycleOwner, event: Event) {
		when (event) {
			Event.ON_START -> {
				registerNetworkCallback()
				L.d("Event.ON_START")
			}

			Event.ON_STOP -> {
				unRegisterNetworkCallback()
				L.d("Event.ON_STOP")
			}
			else -> {}
		}
	}
}