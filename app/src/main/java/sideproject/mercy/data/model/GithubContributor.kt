package sideproject.mercy.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubContributor(
    @SerialName("login") val name: String,
    @SerialName("avatar_url") val photoUrl: String
)