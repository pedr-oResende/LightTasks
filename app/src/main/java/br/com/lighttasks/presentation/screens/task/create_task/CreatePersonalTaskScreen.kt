package br.com.lighttasks.presentation.screens.task.create_task

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.extensions.getArgument
import br.com.lighttasks.commom.util.PreferencesWrapper
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.compose.navigation.Screens
import org.koin.androidx.compose.getViewModel

@Composable
fun CreatePersonalTaskScreen(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModel: CreateTaskViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true) {
        PreferencesWrapper.getInstance()?.getUser()?.let { user ->
            viewModel.setResponsible(user.id)
            viewModel.setTask(
                task = navHostController.previousBackStackEntry?.savedStateHandle?.getArgument<Task>(
                    key = Screens.CreatePersonalTask.argumentKey
                )
            )
        }
    }
    CreateTaskScreen(
        isTaskForTeamMember = false,
        viewModel = viewModel,
        onBackPressedDispatcher = onBackPressedDispatcher,
        navHostController = navHostController
    )
}