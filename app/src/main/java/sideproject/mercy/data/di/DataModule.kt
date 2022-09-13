package sideproject.mercy.data.di

import sideproject.mercy.data.repository.GithubRepositoryImpl
import sideproject.mercy.data.repository.GithubRepository
import sideproject.mercy.data.repository.SearchRepository
import sideproject.mercy.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindGithubRepository(
        repository: GithubRepositoryImpl
    ): GithubRepository

    @Binds
    abstract fun bindSearchRepository(
        repository: SearchRepositoryImpl
    ): SearchRepository
}