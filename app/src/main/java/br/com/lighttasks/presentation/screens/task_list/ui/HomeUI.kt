package br.com.lighttasks.presentation.screens.task_list.ui

import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task

data class HomeUI(
    val tasks: List<Task> = listOf(),
    val filteredTasks: List<Task> = listOf(),
    val priorities: List<Priority> = listOf(),
    val isSearchingTask: Boolean = false,
    val searchText: String = ""
)
