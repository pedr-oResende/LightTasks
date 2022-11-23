package br.com.lighttasks.domain.validator

class ValidationResult (
    val successful: Boolean,
    val errorMessage: String? = null //TODO(trocar para string res)
)