package br.com.lighttasks.domain.validator

class ValidatePassword {
    operator fun invoke(password: String): ValidationResult {
        return if (password.isBlank()) {
            ValidationResult(
                successful = false,
                errorMessage = "Preencha a senha"
            )
        } else if (password.length < 6) {
            ValidationResult(
                successful = false,
                errorMessage = "A senha deve conter pelo menos 6 caracters"
            )
        } else {
            ValidationResult(successful = true)
        }
    }
}