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

        override fun equals(other: Any?): Boolean {
            if (other is Error)
                return this.type == other.type &&
                        (this.code != null || this.code == other.code) &&
                        this.message().equals(other.message())


            return super.equals(other)
        }

        override fun hashCode(): Int {
            var result = type.hashCode()
            result = 31 * result + (code ?: 0)
            result = 31 * result + (messageError?.hashCode() ?: 0)
            return result
        }
    }
}