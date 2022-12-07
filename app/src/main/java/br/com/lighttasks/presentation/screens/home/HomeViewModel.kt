package br.com.lighttasks.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.usecase.task.GetTasksByUserUseCase
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.home.ui.HomeUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTasksUseCase: GetTasksByUserUseCase
) : ViewModel() {

    private val _taskList = MutableStateFlow<StateUI<List<Task>>>(StateUI.Idle())
    val taskList: StateFlow<StateUI<List<Task>>> = _taskList

    private val _homeUI = mutableStateOf(HomeUI())
    val homeUI: State<HomeUI> = _homeUI

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            val userId = PreferencesWrapper.instance?.basicUser?.id ?: return@launch
            getTasksUseCase(userId).onStart {
                _taskList.emit(StateUI.Processing())
            }.catch {
                _taskList.emit(StateUI.Error(it.message.toString()))
            }.collect { data ->
                _homeUI.value = homeUI.value.copy(
                    tasks = data,
                    filteredTasks = data
                )
                _taskList.emit(StateUI.Processed(_homeUI.value.tasks))
            }
        }
    }

    fun refresh() = loadTasks()

    fun actionFilter(
        filter: Priority
    ) {
        homeUI.value.apply {
            if (_homeUI.value.filters.contains(filter))
                _homeUI.value = copy(filters = filters.minus(filter))
            else
                _homeUI.value = copy(filters = filters.plus(filter))
        }
        filter()
    }

    private fun filter() {
        homeUI.value.apply {
            _homeUI.value = copy(
                filteredTasks = if (filters.isEmpty()) {
                    tasks
                } else {
                    tasks.filter { filters.contains(it.priority) }
                }
            )
        }
    }
}
