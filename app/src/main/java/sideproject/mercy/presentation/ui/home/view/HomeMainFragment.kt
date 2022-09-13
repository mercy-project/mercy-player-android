package sideproject.mercy.presentation.ui.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import sideproject.mercy.databinding.FragmentHomeMainBinding
import sideproject.mercy.presentation.ui.home.viewmodel.HomeMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMainFragment : Fragment() {

	private lateinit var binding: FragmentHomeMainBinding

	private val viewModel by viewModels<HomeMainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentHomeMainBinding.inflate(layoutInflater).apply {
			lifecycleOwner = viewLifecycleOwner
			viewModel = this@HomeMainFragment.viewModel
		}

		return binding.root
	}
}