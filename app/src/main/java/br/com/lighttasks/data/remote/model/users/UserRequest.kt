package br.com.lighttasks.data.remote.model.users

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerializedName("username") val username: String?,
    @SerializedName("password") val password: String?,
)