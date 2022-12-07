package br.com.lighttasks.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.util.priority.getPriorityContainerColor
import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.compose.components.DefaultErrorMessage
import br.com.lighttasks.presentation.compose.components.SmallMenuItem
import br.com.lighttasks.presentation.compose.components.TaskItem
import br.com.lighttasks.presentation.compose.widgets.CustomSwipeRefresh
import br.com.lighttasks.presentation.model.StateUI
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun HomeTasksMainScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel
) {
    val tasks = viewModel.taskList.collectAsState().value
    CustomSwipeRefresh(
        swipeRefreshState = SwipeRefreshState(isRefreshing = tasks.loading()),
        onRefresh = { viewModel.refresh() }
    ) {
        when (tasks) {
            is StateUI.Processing -> Box(modifier = Modifier.fillMaxSize())
            is StateUI.Error -> DefaultErrorMessage(message = "Ocorreu um erro inesperado")
            is StateUI.Idle -> Unit
            is StateUI.Processed -> {
                HomeTasksScreen(viewModel = viewModel, tasks = tasks.data)
            }
        }
    }
}

@Composable
fun HomeTasksScreen(
    viewModel: HomeViewModel,
    tasks: List<Task>
) {
    val homeUI = viewModel.homeUI.value
    Column {
        LazyRow(
            modifier = Modifier.padding(all = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val priorities = mutableSetOf<Priority>()
            priorities.addAll(tasks.map { it.priority })
            items(priorities.toList()) { priority ->
                SmallMenuItem(
                    name = priority.toString(),
                    onClick = {
                        viewModel.actionFilter(priority)
                    },
                    color = getPriorityContainerColor(priority)
                )
            }
        }
        LazyColumn {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(homeUI.filteredTasks) { task ->
                TaskItem(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clip(shape = MaterialTheme.shapes.medium)
                        .clickable { },
                    task = task
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}