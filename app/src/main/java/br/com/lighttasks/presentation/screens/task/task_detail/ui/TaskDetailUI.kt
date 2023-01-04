package br.com.lighttasks.presentation.screens.task.task_detail.ui

import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task

data class TaskDetailUI(
    val task: Task? = null,
    val responsible: BasicUser? = null
)