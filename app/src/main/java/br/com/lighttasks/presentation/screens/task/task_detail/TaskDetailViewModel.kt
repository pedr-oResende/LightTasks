package br.com.lighttasks.presentation.screens.task.task_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.usecase.basic_user.GetBasicUserUseCase
import br.com.lighttasks.domain.usecase.task.EditTaskUseCase
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.task.task_detail.ui.TaskDetailUI
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val getBasicUserUseCase: GetBasicUserUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    task: Task?
) : ViewModel() {

    private val _taskDetailUI = mutableStateOf(TaskDetailUI())
    val taskDetailUI: State<TaskDetailUI> = _taskDetailUI

    private val _responsibleResponse = MutableStateFlow<StateUI<BasicUser>>(StateUI.Idle())
    val responsibleResponse: StateFlow<StateUI<BasicUser>> = _responsibleResponse

    private val _finishTaskResponse = MutableStateFlow<StateUI<Task>>(StateUI.Idle())
    val finishTaskResponse: StateFlow<StateUI<Task>> = _finishTaskResponse

    init {
        _taskDetailUI.value = taskDetailUI.value.copy(task = task)
        getResponsible(task?.responsibleId!!)
    }

    private fun getResponsible(id: Long) {
        viewModelScope.launch {
            getBasicUserUseCase(id).onStart {
                _responsibleResponse.emit(StateUI.Processing())
            }.catch {
                _responsibleResponse.emit(StateUI.Error(it.message.toString()))
            }.collect { user ->
                _responsibleResponse.emit(StateUI.Processed(user))
                _taskDetailUI.value = taskDetailUI.value.copy(responsible = user)
            }
        }
    }

    fun finishTask() {
        _taskDetailUI.value.task?.let { task ->
            viewModelScope.launch {
                editTaskUseCase(task.copy(isDone = true)).onStart {
                    _finishTaskResponse.emit(StateUI.Processing())
                }.catch {
                    _finishTaskResponse.emit(StateUI.Error(it.message.toString()))
                }.collect {
                    _finishTaskResponse.emit(StateUI.Processed(it))
                }
            }
        }
    }
}