package br.com.lighttasks.presentation.screens.login.ui

sealed class LoginActions {
    object ToggleLogin : LoginActions()
    data class UsernameTextChanged(val text: String) : LoginActions()
    data class PasswordTextChanged(val text: String) : LoginActions()
    object ShowPassword : LoginActions()
}
