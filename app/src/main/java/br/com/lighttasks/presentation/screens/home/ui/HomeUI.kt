package br.com.lighttasks.presentation.screens.home.ui

import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task

data class HomeUI(
    val tasks: List<Task> = listOf(),
    val filteredTasks: List<Task> = listOf(),
    val filters: List<Priority> = listOf()
)
