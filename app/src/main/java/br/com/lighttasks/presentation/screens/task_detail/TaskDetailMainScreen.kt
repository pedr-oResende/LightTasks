package br.com.lighttasks.presentation.screens.task_detail

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.extensions.getArgument
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import org.koin.androidx.compose.getViewModel

@Composable
fun TaskDetailMainScreen(
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel = getViewModel(),
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    LaunchedEffect(key1 = true) {
        viewModel.loadTask(
            task = navHostController.previousBackStackEntry?.savedStateHandle?.getArgument<Task>(
                key = Screens.TaskDetail.argumentKey
            )
        )
    }
    TaskDetailScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        onBackPressedDispatcher = onBackPressedDispatcher
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    val taskDetailUI = viewModel.taskDetailUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = taskDetailUI.task?.name ?: "Tarefa",
                onBackPressed = {
                    onBackPressedDispatcher.onBackPressed()
                },
                actions = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {

        }
    }
}