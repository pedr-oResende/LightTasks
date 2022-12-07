package br.com.lighttasks.commom.model


sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(
        val type: Type,
        val code: Int? = null,
        private val messageError: Message? = null
    ) : Result<Nothing>() {
        enum class Type {
            NETWORK, HTTP, GENERIC
        }

        fun message(): String? = messageError?.error?.message
        fun messageErrorCode(): Int? = messageError?.error?.code
    }
}