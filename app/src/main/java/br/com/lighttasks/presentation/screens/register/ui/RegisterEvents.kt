package br.com.lighttasks.presentation.screens.register.ui

sealed class RegisterEvents {
    object ToggleRegister : RegisterEvents()
    data class UsernameTextChanged(val text: String) : RegisterEvents()
    data class FullNameTextChanged(val text: String) : RegisterEvents()
    data class PasswordTextChanged(val text: String) : RegisterEvents()
    object ShowPassword : RegisterEvents()
}
