package sideproject.mercy.presentation.ui.search.main.adapter.multitype

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import sideproject.mercy.presentation.base.adapter.BaseBindingViewHolder
import sideproject.mercy.presentation.ui.search.main.adapter.multitype.SearchViewType.ITEM

abstract class SearchViewHolder<VM : ISearchItemViewModel, B : ViewDataBinding>(
	itemView: View
) : BaseBindingViewHolder<VM, B>(itemView) {

	companion object {
		@Suppress("UNCHECKED_CAST")
		fun getViewHolder(
			parent: ViewGroup,
			viewType: SearchViewType
		): SearchViewHolder<ISearchItemViewModel, ViewDataBinding> {
			return when (viewType) {
				ITEM -> SearchItemViewHolder.newInstance(parent)
			} as SearchViewHolder<ISearchItemViewModel, ViewDataBinding>
		}
	}
}