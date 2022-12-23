package br.com.lighttasks.presentation.screens.task_list.ui

import br.com.lighttasks.domain.model.Priority

sealed interface TasksEvents {
    data class FilterByPriority(val filter: Priority) : TasksEvents
    data class SearchTextChanged(val text: String): TasksEvents
    object OpenSearchBar : TasksEvents
    object CloseSearchBar : TasksEvents
}