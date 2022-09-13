package sideproject.mercy.presentation.ui.search.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import sideproject.mercy.databinding.FragmentBookDetailBinding
import sideproject.mercy.presentation.ui.search.detail.viewmodel.BookDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment() {

	private lateinit var binding: FragmentBookDetailBinding

	private val viewModel by viewModels<BookDetailViewModel>()
	private val bookDetailArgs by navArgs<BookDetailFragmentArgs>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentBookDetailBinding.inflate(layoutInflater).apply {
			lifecycleOwner = viewLifecycleOwner
			viewModel = this@BookDetailFragment.viewModel
		}

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initData()
	}

	private fun initData() {
		viewModel.setBookInfo(bookDetailArgs.bookInfo)
	}
}