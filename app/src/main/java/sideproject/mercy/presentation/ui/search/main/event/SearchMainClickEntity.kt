package sideproject.mercy.presentation.ui.search.main.event

import sideproject.mercy.domain.entity.ClickEntity
import sideproject.mercy.domain.model.BookInfoItemViewModel

sealed class SearchMainClickEntity : ClickEntity() {
	data class ClickBookInfo(val bookInfoItemViewModel: BookInfoItemViewModel) : SearchMainClickEntity()
}