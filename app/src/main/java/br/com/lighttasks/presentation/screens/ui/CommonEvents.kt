package br.com.lighttasks.presentation.screens.ui

sealed class CommonEvents {
    object GenericError : CommonEvents()
    data class InvalidFieldsError(val message: String) : CommonEvents()
}