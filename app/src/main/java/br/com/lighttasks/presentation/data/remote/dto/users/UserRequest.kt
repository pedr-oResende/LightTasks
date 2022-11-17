package br.com.lighttasks.presentation.data.remote.dto.users

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerializedName("id") val id: Long?,
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
)