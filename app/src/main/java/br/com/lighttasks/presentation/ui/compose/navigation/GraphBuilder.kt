package br.com.lighttasks.presentation.ui.compose.navigation

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.ui.screens.home.HomeTasksScreen
import br.com.lighttasks.presentation.ui.screens.login.LoginScreen
import br.com.lighttasks.presentation.ui.screens.profile.ProfileScreen
import br.com.lighttasks.presentation.ui.screens.register.RegisterScreen
import br.com.lighttasks.presentation.ui.screens.teams.TeamsScreen
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.register(
    navHostController: NavHostController,
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    composable(
        route = Screens.Register.route
    ) {
        RegisterScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.login(
    navHostController: NavHostController
) {
    composable(
        route = Screens.Login.route
    ) {
        LoginScreen()
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.tasks(
    navHostController: NavHostController
) {
    composable(
        route = Screens.Tasks.route
    ) {
        HomeTasksScreen()
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
        HomeTasksScreen()
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
        TeamsScreen()
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
