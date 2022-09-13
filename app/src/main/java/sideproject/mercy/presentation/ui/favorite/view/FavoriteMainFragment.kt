package sideproject.mercy.presentation.ui.favorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import sideproject.mercy.databinding.FragmentFavoriteMainBinding
import sideproject.mercy.presentation.ui.favorite.viewmodel.FavoriteMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMainFragment : Fragment() {

	private lateinit var binding: FragmentFavoriteMainBinding

	private val viewModel by viewModels<FavoriteMainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentFavoriteMainBinding.inflate(layoutInflater).apply {
			lifecycleOwner = viewLifecycleOwner
			viewModel = this@FavoriteMainFragment.viewModel
		}

		return binding.root
	}
}