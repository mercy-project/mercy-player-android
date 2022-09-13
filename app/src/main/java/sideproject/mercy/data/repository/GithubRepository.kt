package sideproject.mercy.data.repository

import sideproject.mercy.shared.model.User

interface GithubRepository {
    suspend fun getContributors(
        owner: String,
        name: String,
        pageNo: Int
    ): List<User>
}