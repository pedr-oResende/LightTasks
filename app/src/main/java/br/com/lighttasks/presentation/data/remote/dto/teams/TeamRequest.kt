package br.com.lighttasks.presentation.data.remote.dto.teams

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TeamRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("members") val members: List<Long>?,
    @SerializedName("leader_id") val leaderId: String?
)