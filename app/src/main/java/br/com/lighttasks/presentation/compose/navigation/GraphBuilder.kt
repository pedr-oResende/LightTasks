package br.com.lighttasks.presentation.compose.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.screens.home.HomeScreen
import br.com.lighttasks.presentation.screens.login.LoginMainScreen
import br.com.lighttasks.presentation.screens.login.LoginViewModel
import br.com.lighttasks.presentation.screens.register.RegisterMainScreen
import br.com.lighttasks.presentation.screens.register.RegisterViewModel
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.register(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    snackbarHost: SnackbarHostState
) {
    composable(
        route = Screens.Register.route
    ) {
        val viewModel = getViewModel<RegisterViewModel>()
        RegisterMainScreen(
            onBackPressedDispatcher = onBackPressedDispatcher,
            viewModel = viewModel,
            snackbarHost = snackbarHost
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.login(
    navHostController: NavHostController,
    snackbarHost: SnackbarHostState
) {
    composable(
        route = Screens.Login.route
    ) {
        val viewModel = getViewModel<LoginViewModel>()
        LoginMainScreen(
            navHostController = navHostController,
            viewModel = viewModel,
            snackbarHost = snackbarHost
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.home(
    navHostController: NavHostController,
    snackBarHost: SnackbarHostState
) {
    composable(
        route = Screens.Tasks.route
    ) {
        HomeScreen(
            navHostController = navHostController,
            snackbarHostState = snackBarHost
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.taskDetail(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Screens.TaskDetail.route
    ) {

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.teamDetail(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Screens.TeamDetail.route
    ) {

    }
}