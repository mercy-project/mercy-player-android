package sideproject.mercy.presentation.ui.search.detail.viewmodel

import androidx.databinding.ObservableField
import sideproject.mercy.domain.model.BookInfoItemViewModel
import sideproject.mercy.presentation.base.viewmodel.BaseViewModel

class BookDetailViewModel : BaseViewModel() {

    val bookInfoItemViewModel =  ObservableField<BookInfoItemViewModel>()

    fun setBookInfo(bookInfoItemViewModel: BookInfoItemViewModel) {
        this.bookInfoItemViewModel.set(bookInfoItemViewModel)
    }

    fun onClickLike() {
        bookInfoItemViewModel.get()?.isLike?.let {
            if (it.get()) {
                likeOff()
            } else {
                likeOn()
            }
        }
    }

    private fun likeOn() {
        bookInfoItemViewModel.get()?.isLike?.set(true)
    }

    private fun likeOff() {
        bookInfoItemViewModel.get()?.isLike?.set(false)
    }
}