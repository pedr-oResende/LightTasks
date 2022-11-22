package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.model.NavigationBarItem

@Composable
fun DefaultNavigationBar(
    navHostController: NavHostController,
    content: @Composable (Modifier) -> Unit
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
            Icons.Default.Groups
        ),
        NavigationBarItem(
            name = "Profile",
            route = Screens.Profile.route,
            icon = Icons.Default.AccountCircle
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        content(Modifier.weight(1f))
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