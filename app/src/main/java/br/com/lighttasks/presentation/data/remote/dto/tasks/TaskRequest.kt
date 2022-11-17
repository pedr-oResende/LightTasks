package br.com.lighttasks.presentation.data.remote.dto.tasks

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TaskRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("deadline") val deadline: String?,
    @SerializedName("team_id") val teamId: String?,
    @SerializedName("responsible_id") val responsibleId: String?,
    @SerializedName("is_done") val isDone: Boolean?,

)