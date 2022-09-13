package sideproject.mercy.domain.model

import android.os.Parcelable
import androidx.databinding.ObservableBoolean
import sideproject.mercy.presentation.ClickEventNotifier
import sideproject.mercy.presentation.ui.search.main.adapter.multitype.ISearchItemViewModel
import sideproject.mercy.presentation.ui.search.main.event.SearchMainClickEntity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BookInfoItemViewModel(
    val authors: List<String>,
    val translators: List<String>,
    val contents: String,
    val datetime: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val url: String,
    val bookLike: Boolean = false,
	val clickEventNotifier: @RawValue ClickEventNotifier
): Parcelable, ISearchItemViewModel {
	override val itemId = getViewTypeName() + title

    val isLike by lazy { ObservableBoolean(bookLike) }

	fun clickBookInfo() {
		clickEventNotifier.notifyClickEvent(
			entity = SearchMainClickEntity.ClickBookInfo(this)
		)
	}
}