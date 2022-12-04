package br.com.lighttasks.presentation.compose.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.screens.home.HomeTasksMainScreen
import br.com.lighttasks.presentation.screens.home.HomeViewModel
import br.com.lighttasks.presentation.screens.login.LoginMainScreen
import br.com.lighttasks.presentation.screens.login.LoginViewModel
import br.com.lighttasks.presentation.screens.profile.ProfileScreen
import br.com.lighttasks.presentation.screens.register.RegisterMainScreen
import br.com.lighttasks.presentation.screens.teams.TeamsScreen
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.register(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Screens.Register.route
    ) {
        RegisterMainScreen()
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
fun NavGraphBuilder.tasks(
    navHostController: NavHostController
) {
    composable(
        route = Screens.Tasks.route
    ) {
        val viewModel = getViewModel<HomeViewModel>()
        HomeTasksMainScreen(
            navHostController = navHostController,
            viewModel = viewModel
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
fun NavGraphBuilder.teams(
    navHostController: NavHostController
) {
    composable(
        route = Screens.Teams.route
    ) {
        TeamsScreen()
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profile(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Screens.Profile.route
    ) {
        ProfileScreen()
    }
}
