package br.com.lighttasks.data.remote.model.tasks

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TaskResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("deadline") val deadline: String?,
    @SerializedName("team_id") val teamId: Long?,
    @SerializedName("responsible_id") val responsibleId: Long?,
    @SerializedName("is_done") val isDone: Boolean?,

)