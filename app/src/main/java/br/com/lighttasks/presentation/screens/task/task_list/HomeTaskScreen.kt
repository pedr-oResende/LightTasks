package br.com.lighttasks.presentation.screens.task.task_list

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.util.priority.getPriorityContainerColor
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.compose.components.DefaultFilterChip
import br.com.lighttasks.presentation.compose.components.state.error.DefaultErrorMessage
import br.com.lighttasks.presentation.compose.components.task.TaskList
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.compose.widgets.CustomSwipeRefresh
import br.com.lighttasks.presentation.compose.widgets.top_bar.SearchTopBar
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import br.com.lighttasks.presentation.model.StateUI
import br.com.lighttasks.presentation.screens.task.task_list.ui.HomeUI
import br.com.lighttasks.presentation.screens.task.task_list.ui.TasksEvents
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTasksMainScreen(
    navHostController: NavHostController,
    viewModel: TasksViewModel = getViewModel()
) {
    val homeUI = viewModel.homeUI.value
    val (filtersVisibility, setFiltersVisibility) = remember { mutableStateOf(false) }
    val tasks = viewModel.taskList.collectAsState().value
    Scaffold(
        topBar = {
            if (homeUI.isSearchingTask) {
                SearchTopBar(
                    searchText = homeUI.searchText,
                    placeholderText = "Nome da tarefa",
                    onSearchTextChanged = { text ->
                        viewModel.onEvent(TasksEvents.SearchTextChanged(text))
                    },
                    onClearClick = { viewModel.onEvent(TasksEvents.CloseSearchBar) }
                )
            } else {
                TopBar(
                    title = "Tarefas",
                    actions = {
                        IconButton(onClick = {
                            viewModel.onEvent(TasksEvents.OpenSearchBar)
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                        }
                        IconButton(onClick = {
                            setFiltersVisibility(filtersVisibility.not())
                            viewModel.resetFilters()
                        }) {
                            Icon(
                                imageVector = if (filtersVisibility)
                                    Icons.Default.FilterAltOff
                                else
                                    Icons.Default.FilterAlt,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screens.CreatePersonalTask.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            CustomSwipeRefresh(
                swipeRefreshState = SwipeRefreshState(isRefreshing = tasks.loading()),
                onRefresh = { viewModel.refresh() }
            ) {
                when (tasks) {
                    is StateUI.Processing -> Box(modifier = Modifier.fillMaxSize())
                    is StateUI.Error -> DefaultErrorMessage(message = "Ocorreu um erro inesperado")
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        HomeTasksScreen(
                            viewModel = viewModel,
                            homeUI = homeUI,
                            navHostController = navHostController,
                            filtersVisibility = filtersVisibility
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTasksScreen(
    viewModel: TasksViewModel,
    navHostController: NavHostController,
    filtersVisibility: Boolean,
    homeUI: HomeUI
) {
    AnimatedVisibility(
        visible = filtersVisibility,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val priorities = homeUI.tasks
                    .map { it.priority }
                    .distinct()
                    .sortedBy { it.ordinal }
                items(priorities) { priority ->
                    DefaultFilterChip(
                        name = priority.toString(),
                        onClick = {
                            viewModel.onEvent(TasksEvents.FilterByPriority(priority))
                        },
                        color = getPriorityContainerColor(priority)
                    )
                }
            }
        }
    }
    val onItemClick: (Task) -> Unit = { task ->
        Screens.TaskDetail.navigateWithArgument(
            navHostController = navHostController,
            argumentValue = task
        )
    }
    val onItemLongClick: (Task) -> Unit = { task ->

    }
    TaskList(
        tasks = homeUI.filteredTasks,
        onItemClick = onItemClick,
        onItemLongClick = onItemLongClick
    )
}