package sideproject.mercy.domain.github

import sideproject.mercy.data.repository.GithubRepository
import sideproject.mercy.domain.UseCase
import sideproject.mercy.shared.IoDispatcher
import sideproject.mercy.shared.model.User
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import sideproject.mercy.domain.github.GetContributorsUseCase.Param

class GetContributorsUseCase @Inject constructor(
    private val githubRepository: GithubRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Param, List<User>>(dispatcher) {

    override suspend fun execute(param: Param): List<User> {
        return githubRepository.getContributors(
            owner = param.owner,
            name = param.name,
            pageNo = param.pageNo
        )
    }

    data class Param(
        val owner: String,
        val name: String,
        val pageNo: Int
    )
}