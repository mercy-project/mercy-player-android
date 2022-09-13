package sideproject.mercy.presentation.ui.search.main.adapter.multitype

import sideproject.mercy.domain.model.BookInfoItemViewModel
import sideproject.mercy.presentation.base.adapter.IViewTypeGetter
import sideproject.mercy.presentation.ui.search.main.adapter.multitype.SearchViewType.ITEM

interface ISearchItemViewModel : IViewTypeGetter<SearchViewType> {

	val itemId: String

	override fun getViewType(): SearchViewType {
		return when (this) {
			is BookInfoItemViewModel -> ITEM
			else -> ITEM
		}
	}
}