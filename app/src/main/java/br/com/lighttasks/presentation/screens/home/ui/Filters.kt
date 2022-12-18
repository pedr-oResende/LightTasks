package br.com.lighttasks.presentation.screens.home.ui

sealed class Filters {
    object Priority: Filters()
    object NameOrDescription: Filters()
}
