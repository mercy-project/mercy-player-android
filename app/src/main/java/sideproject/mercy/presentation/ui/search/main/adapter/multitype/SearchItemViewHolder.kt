package sideproject.mercy.presentation.ui.search.main.adapter.multitype

import android.view.View
import android.view.ViewGroup
import sideproject.mercy.R.layout
import sideproject.mercy.databinding.ItemBookBinding
import sideproject.mercy.domain.model.BookInfoItemViewModel
import sideproject.mercy.utils.extensions.createView

class SearchItemViewHolder(
	itemView: View,
) : SearchViewHolder<BookInfoItemViewModel, ItemBookBinding>(itemView) {
    companion object {
        fun newInstance(parent: ViewGroup) =
            SearchItemViewHolder(parent.createView(layout.item_book))
    }

    override fun onBind(item: BookInfoItemViewModel, position: Int) {
        binding?.run {
            this.item = item
            executePendingBindings()
        }
    }
}
