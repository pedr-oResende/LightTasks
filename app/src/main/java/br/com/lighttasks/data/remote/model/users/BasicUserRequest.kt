package br.com.lighttasks.data.remote.model.users

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BasicUserRequest(
    @SerializedName("username") val username: String?,
    @SerializedName("full_name") val fullName: String?,
)