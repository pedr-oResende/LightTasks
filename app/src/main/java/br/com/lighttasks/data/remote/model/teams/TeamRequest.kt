package br.com.lighttasks.data.remote.model.teams

import br.com.lighttasks.data.remote.model.users.BasicUserRequest
import br.com.lighttasks.domain.model.BasicUser
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TeamRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("members") val members: List<BasicUserRequest>?,
    @SerializedName("leader_id") val leaderId: Long?
)