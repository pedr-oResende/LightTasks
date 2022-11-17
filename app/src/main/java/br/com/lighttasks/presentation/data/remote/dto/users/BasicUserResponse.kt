package br.com.lighttasks.presentation.data.remote.dto.users

import br.com.lighttasks.presentation.data.remote.dto.tasks.TaskResponse
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BasicUserResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("tasks") val tasks: List<TaskResponse>?,
    @SerializedName("teams_id") val TeamsId: List<Int>?,
)