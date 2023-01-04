package br.com.lighttasks.presentation.screens.task.create_task.ui

import br.com.lighttasks.domain.model.BasicUser

sealed class CreateTaskEvents {
    data class TaskNameChanged(val text: String) : CreateTaskEvents()
    data class TaskDescriptionChanged(val text: String) : CreateTaskEvents()
    data class TaskResponsibleChanged(val responsible: BasicUser) : CreateTaskEvents()
    data class TaskDeadlineChanged(val text: String) : CreateTaskEvents()
}