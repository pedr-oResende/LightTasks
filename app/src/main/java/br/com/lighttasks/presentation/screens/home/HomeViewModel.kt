package br.com.lighttasks.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.usecase.task.GetTasksByUserUseCase
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.home.ui.HomeEvents
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

    fun onEvent(event: HomeEvents) {
        event.run {
            when (this) {
                is HomeEvents.FilterByPriority -> {
                    homeUI.value.apply {
                        if (_homeUI.value.priorities.contains(filter)) {
                            _homeUI.value = copy(priorities = priorities.minus(filter))
                        } else {
                            _homeUI.value = copy(priorities = priorities.plus(filter))
                        }
                    }
                    filter()
                }
                is HomeEvents.SearchTextChanged -> {
                    _homeUI.value = homeUI.value.copy(
                        searchText = text
                    )
                    filter()
                }
                is HomeEvents.CloseSearchBar -> {
                    _homeUI.value = homeUI.value.copy(
                        isSearchingTask = false,
                        searchText = ""
                    )
                    filter()
                }
                is HomeEvents.OpenSearchBar -> {
                    _homeUI.value = homeUI.value.copy(
                        isSearchingTask = true
                    )
                }
            }
        }
    }


    private fun loadTasks() {
        viewModelScope.launch {
            val userId = PreferencesWrapper.instance?.basicUser?.id
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

    private fun filter() {
        homeUI.value.apply {
            _homeUI.value = copy(
                filteredTasks = tasks
                    .filter { task ->
                        filterByPriority(task)
                    }
                    .filter { task ->
                        filterByNameOrDescription(task)
                    }
            )
        }
    }

    private fun filterByPriority(task: Task) = _homeUI.value.run {
        if (priorities.isNotEmpty()) {
            priorities.contains(task.priority)
        } else {
            true
        }
    }

    private fun filterByNameOrDescription(task: Task) = _homeUI.value.run {
        if (searchText.isNotBlank()) {
            task.name?.contains(searchText, ignoreCase = true) == true
                    || task.description?.contains(searchText, ignoreCase = true) == true
        } else {
            true
        }
    }
}
