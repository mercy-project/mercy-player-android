package sideproject.mercy.domain.model

data class PagingMeta(
    val isEnd: Boolean,
    val currentPage: Int = 1
)
