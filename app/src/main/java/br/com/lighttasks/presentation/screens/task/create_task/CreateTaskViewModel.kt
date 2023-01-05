package br.com.lighttasks.presentation.screens.task.create_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.commom.util.date.DateUtils
import br.com.lighttasks.domain.model.BasicUser
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.usecase.basic_user.GetBasicUserUseCase
import br.com.lighttasks.domain.usecase.task.CreateTaskUseCase
import br.com.lighttasks.domain.usecase.task.EditTaskUseCase
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.task.create_task.ui.CreateTaskEvents
import br.com.lighttasks.presentation.screens.task.create_task.ui.CreateTaskUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val getBasicUserUseCase: GetBasicUserUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val editTaskUseCase: EditTaskUseCase,
    task: Task?,
    responsibleId: Long?
) : ViewModel() {

    private val _createTaskUI = mutableStateOf(CreateTaskUI())
    val createTaskUI: State<CreateTaskUI> = _createTaskUI

    private val _responsibleResponse = MutableStateFlow<StateUI<BasicUser>>(StateUI.Idle())
    val responsibleResponse: StateFlow<StateUI<BasicUser>> = _responsibleResponse

    fun onEvent(event: CreateTaskEvents) {
        when (event) {
            is CreateTaskEvents.TaskDeadlineChanged -> {
                _createTaskUI.value = createTaskUI.value.copy(
                    deadline = event.text
                )
            }
            is CreateTaskEvents.TaskDescriptionChanged -> {
                _createTaskUI.value = createTaskUI.value.copy(
                    description = event.text
                )
            }
            is CreateTaskEvents.TaskNameChanged -> {
                _createTaskUI.value = createTaskUI.value.copy(
                    name = event.text
                )
            }
            is CreateTaskEvents.TaskResponsibleChanged -> {
                _createTaskUI.value = createTaskUI.value.copy(
                    responsible = event.responsible
                )
            }
        }
    }

    init {
        setTask(task)
        if (responsibleId != null)
            setResponsible(responsibleId)
    }

    private fun setResponsible(id: Long) {
        viewModelScope.launch {
            getBasicUserUseCase(id).onStart {
                _responsibleResponse.emit(StateUI.Processing())
            }.catch {
                _responsibleResponse.emit(StateUI.Error(it.message.toString()))
            }.collect { user ->
                _responsibleResponse.emit(StateUI.Processed(user))
                _createTaskUI.value = createTaskUI.value.copy(responsible = user)
            }
        }
    }

    private fun setTask(task: Task?) {
        _createTaskUI.value = createTaskUI.value.copy(
            task = task,
            isEditingTask = task != null,
            name = task?.name.orEmpty(),
            description =  task?.description.orEmpty(),
            deadline = task?.deadline.orEmpty()
        )
    }

}