package br.com.lighttasks.presentation.screens.task.create_task.ui

import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task

data class CreateTaskUI(
    val task: Task? = null,
    val name: String = "",
    val description: String = "",
    val deadline: String = "",
    val responsibleId: BasicUser? = null,
    val isEditingTask: Boolean = false
)