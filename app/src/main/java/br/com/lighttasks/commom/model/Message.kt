package br.com.lighttasks.commom.model

import java.io.Serializable

data class Message(
    val message: String,
    val error: MessageError,
    val success: Boolean
) : Serializable