package sideproject.mercy.data.repository

import sideproject.mercy.domain.book.SearchBooksInfoUseCase.Params
import sideproject.mercy.domain.model.SearchBooksData

interface SearchRepository {
    suspend fun searchBooks(param: Params): SearchBooksData
}