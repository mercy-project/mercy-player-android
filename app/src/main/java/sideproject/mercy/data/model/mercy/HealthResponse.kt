package sideproject.mercy.data.model.mercy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(
	@SerialName("health")
	val isAlive: Boolean
)