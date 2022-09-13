package sideproject.mercy.presentation.ui.search.main.adapter.simple

import sideproject.mercy.R
import sideproject.mercy.domain.model.BookInfoItemViewModel
import sideproject.mercy.presentation.base.adapter.ItemDiffCallback
import sideproject.mercy.presentation.base.adapter.SimpleListBindingAdapter

// ViewType 이 하나일때 적용 예
class SearchMainAdapter : SimpleListBindingAdapter<BookInfoItemViewModel>(
    ItemDiffCallback(
        onItemsTheSame = { old, new ->
            old.title == new.title &&
                old.contents == new.contents &&
                old.thumbnail == new.thumbnail &&
                old.datetime == new.datetime
        },
        onContentsTheSame = { old, new -> old == new }
    )) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_book
    }
}