package br.com.lighttasks.presentation.screens.login.ui

sealed class LoginEvents {

    object GenericError : LoginEvents()
    data class InvalidFieldsError(val message: String) : LoginEvents()

}