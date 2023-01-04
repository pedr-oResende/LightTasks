package br.com.lighttasks.presentation.compose.navigation

import androidx.navigation.NavHostController
import br.com.lighttasks.commom.extensions.putArgument

sealed class Screens(val route: String, val argumentKey: String) {

    fun <T> passArgument(argument: T?): String {
        return this.route.replace(oldValue = "{$argumentKey}", newValue = argument.toString())
    }

    fun backToScreen(navHostController: NavHostController) {
        navHostController.navigate(route = route) {
            popUpTo(0)
        }
    }

    fun <T> navigateWithArgument(
        navHostController: NavHostController,
        argumentValue: T?
    ) {
        navHostController.currentBackStackEntry?.savedStateHandle?.putArgument(
            key = argumentKey,
            argument = argumentValue
        )
        navHostController.navigate(route)
    }

    fun <T> navigateWithListArgument(
        navHostController: NavHostController,
        argumentValue: List<T>?
    ) {
        navHostController.currentBackStackEntry?.savedStateHandle?.set(
            key = argumentKey,
            value = argumentValue
        )
        navHostController.navigate(route)
    }

    object Register : Screens(
        route = "register",
        argumentKey = "tasks_argument"
    )


    object Login : Screens(
        route = "login",
        argumentKey = "login_argument"
    )

    object Tasks : Screens(
        route = "tasks?tasks_argument={tasks_argument}",
        argumentKey = "tasks_argument"
    )

    object TaskDetail : Screens(
        route = "task_detail",
        argumentKey = "task_detail_argument"
    )

    object CreateTaskForTeamMember : Screens(
        route = "create_task_for_team_member",
        argumentKey = "create_task_for_team_member_argument"
    )

    object CreatePersonalTask : Screens(
        route = "create_personal_task",
        argumentKey = "create_personal_task_argument"
    )

    object Teams : Screens(
        route = "groups?groups_argument={groups_argument}",
        argumentKey = "groups_argument"
    )

    object TeamDetail : Screens(
        route = "group_detail?group_detail_argument={group_detail_argument}",
        argumentKey = "group_detail_argument"
    )

    object Profile : Screens(
        route = "profile?profile_argument={profile_argument}",
        argumentKey = "profile_argument"
    )
}