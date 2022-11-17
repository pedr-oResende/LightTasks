package br.com.lighttasks.commom.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MessageError(
    @SerializedName("error_code") val code: Int? = null,
    @SerializedName("error_message") val message: String? = null
) : Serializable