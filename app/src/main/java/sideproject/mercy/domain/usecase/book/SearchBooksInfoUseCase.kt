package sideproject.mercy.domain.usecase.book

import sideproject.mercy.data.repository.SearchRepository
import sideproject.mercy.domain.UseCase
import sideproject.mercy.domain.usecase.book.SearchBooksInfoUseCase.Params
import sideproject.mercy.domain.model.SearchBooksData
import sideproject.mercy.presentation.ClickEventNotifier
import sideproject.mercy.shared.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class SearchBooksInfoUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Params, SearchBooksData>(dispatcher) {

    override suspend fun execute(param: Params): SearchBooksData {
        return searchRepository.searchBooks(param)
    }

    data class Params(
        val query: String,
        val page: Int = 1,
	    val clickEventNotifier: ClickEventNotifier
    )
}