package br.com.lighttasks.presentation.screens.login.ui

sealed class LoginEvents {
    object ToggleLogin : LoginEvents()
    data class UsernameTextChanged(val text: String) : LoginEvents()
    data class PasswordTextChanged(val text: String) : LoginEvents()
    object ShowPassword : LoginEvents()
}
