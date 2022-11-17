package br.com.lighttasks.data.remote.model.teams

import br.com.lighttasks.data.remote.model.users.BasicUserResponse
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("members") val members: List<BasicUserResponse>?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("leader_id") val leaderId: Long?
)