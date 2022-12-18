package br.com.lighttasks.presentation.screens.home.ui

import br.com.lighttasks.domain.model.Priority

sealed class HomeEvents {
    data class FilterByPriority(val filter: Priority) : HomeEvents()
    data class SearchTextChanged(val text: String): HomeEvents()
    object OpenSearchBar : HomeEvents()
    object CloseSearchBar : HomeEvents()
}