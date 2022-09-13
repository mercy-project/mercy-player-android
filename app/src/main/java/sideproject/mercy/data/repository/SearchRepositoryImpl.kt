package sideproject.mercy.data.repository

import sideproject.mercy.data.api.KakaoApi
import sideproject.mercy.domain.book.SearchBooksInfoUseCase.Params
import sideproject.mercy.domain.model.SearchBooksData
import sideproject.mercy.presentation.ui.search.main.viewmodel.SearchViewModelMapper
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val kakaoApi: KakaoApi
): SearchRepository {
    override suspend fun searchBooks(param: Params): SearchBooksData {
        val searchBooks = kakaoApi.searchBooks(
            query = param.query,
            page = param.page
        )

        val books = SearchViewModelMapper.getBooksInfo(
	        documents = searchBooks.documents.orEmpty(),
	        clickEventNotifier = param.clickEventNotifier
        )
        val pagingMeta = SearchViewModelMapper.getPagingMeta(searchBooks.meta, param.page)

        return SearchBooksData(
            books = books,
            meta = pagingMeta
        )
    }
}