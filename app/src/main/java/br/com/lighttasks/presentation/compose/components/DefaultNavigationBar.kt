package br.com.lighttasks.presentation.compose.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import br.com.lighttasks.presentation.model.NavigationBarItem

@Composable
fun DefaultNavigationBar(
    navigationBarItems: List<NavigationBarItem>,
    onItemClick: (String) -> Unit,
    isSelected: (String) -> Boolean
) {
    NavigationBar {
        navigationBarItems.forEach { item ->
            NavigationBarItem(
                selected = isSelected(item.route),
                label = {
                    Text(text = item.name, style = MaterialTheme.typography.labelMedium)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                },
                onClick = {
                    onItemClick(item.route)
                }
            )
        }
    }
}