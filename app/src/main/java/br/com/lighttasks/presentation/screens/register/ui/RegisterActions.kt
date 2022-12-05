package br.com.lighttasks.presentation.screens.register.ui

sealed class RegisterActions {
    object ToggleRegister : RegisterActions()
    data class UsernameTextChanged(val text: String) : RegisterActions()
    data class FullNameTextChanged(val text: String) : RegisterActions()
    data class PasswordTextChanged(val text: String) : RegisterActions()
    object ShowPassword : RegisterActions()
}
