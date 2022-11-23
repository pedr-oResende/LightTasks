package br.com.lighttasks.domain.validator

class ValidateUsername {
    operator fun invoke(username: String): ValidationResult {
        return if (username.isNotBlank()) {
            ValidationResult(
                successful = true
            )
        } else {
            ValidationResult(
                successful = false,
                errorMessage = "Preencha o nome de usuário"
            )
        }
    }
}