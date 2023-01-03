package br.com.lighttasks.presentation.screens.task_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.screens.task_detail.ui.TaskDetailUI

class TaskDetailViewModel : ViewModel() {

    private val _taskDetailUI = mutableStateOf(TaskDetailUI())
    val taskDetailUI: State<TaskDetailUI> = _taskDetailUI

    fun loadTask(task: Task?) {
        _taskDetailUI.value = taskDetailUI.value.copy(task = task)
    }
}