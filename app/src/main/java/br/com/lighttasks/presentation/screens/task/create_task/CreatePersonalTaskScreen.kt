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
import org.koin.core.parameter.parametersOf

@Composable
fun CreatePersonalTaskScreen(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModel: CreateTaskViewModel = getViewModel {
        parametersOf(
            navHostController.previousBackStackEntry?.savedStateHandle?.getArgument<Task>(
                key = Screens.CreatePersonalTask.argumentKey
            ),
            PreferencesWrapper.getInstance()?.getUser()?.id
        )
    }
) {
    CreateTaskScreen(
        isTaskForTeamMember = false,
        viewModel = viewModel,
        onBackPressedDispatcher = onBackPressedDispatcher,
        navHostController = navHostController
    )
}