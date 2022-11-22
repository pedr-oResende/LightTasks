package br.com.lighttasks.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)
