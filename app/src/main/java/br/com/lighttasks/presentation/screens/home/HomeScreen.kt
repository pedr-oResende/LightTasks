package br.com.lighttasks.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.compose.components.DefaultNavigationBar
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.model.NavigationBarItem
import br.com.lighttasks.presentation.screens.task_list.HomeTasksMainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    val navigationBarItems = listOf(
        NavigationBarItem(
            name = "Tasks",
            route = Screens.Tasks.route,
            icon = Icons.Default.Menu
        ),
        NavigationBarItem(
            name = "Teams",
            route = Screens.Teams.route,
            icon = Icons.Default.Groups
        ),
        NavigationBarItem(
            name = "Profile",
            route = Screens.Profile.route,
            icon = Icons.Default.AccountCircle
        )
    )
    val currentScreen = remember { mutableStateOf(navigationBarItems.first().route) }
    Scaffold(
        bottomBar = {
            DefaultNavigationBar(
                navigationBarItems = navigationBarItems,
                isSelected = { route ->
                    currentScreen.value == route
                },
                onItemClick = { route ->
                    currentScreen.value = route
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
        ) {
            when (currentScreen.value) {
                Screens.Tasks.route -> {
                    HomeTasksMainScreen(
                        navHostController = navHostController
                    )
                }
                Screens.Teams.route -> {

                }
                Screens.Profile.route -> {

                }
            }
        }
    }
}