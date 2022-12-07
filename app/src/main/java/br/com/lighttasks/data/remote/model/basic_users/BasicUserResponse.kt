package br.com.lighttasks.data.remote.model.basic_users

import br.com.lighttasks.data.remote.model.tasks.TaskResponse
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BasicUserResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("full_name") val fullName: String?,
    @SerializedName("tasks") val tasks: List<TaskResponse>?,
    @SerializedName("teams_id") val teamsId: List<Long>?
)