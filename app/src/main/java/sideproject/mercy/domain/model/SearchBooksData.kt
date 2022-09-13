package sideproject.mercy.domain.model

data class SearchBooksData(
    val books: List<BookInfoItemViewModel>,
    val meta: PagingMeta
)