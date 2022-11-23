package br.com.lighttasks.presentation.compose.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.model.NavigationBarItem

@Composable
fun DefaultNavigationBar(
    navHostController: NavHostController
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
    if (navigationBarItems.map { it.route }.contains(navHostController.currentDestination?.route)) {
        NavigationBar {
            val isSelected = remember { mutableStateOf(false) }
            navigationBarItems.forEach { item ->
                isSelected.value = item.route == navHostController.currentDestination?.route
                NavigationBarItem(
                    selected = isSelected.value,
                    label = {
                        Text(text = item.name, style = MaterialTheme.typography.labelMedium)
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = null)
                    },
                    onClick = {
                        navHostController.navigate(item.route)
                    }
                )
            }
        }
    }
}