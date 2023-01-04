package br.com.lighttasks.commom.util.priority

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.lighttasks.domain.model.Priority

@Composable
fun getPriorityContainerColor(priority: Priority?): Color {
    return when (priority) {
        Priority.Low -> MaterialTheme.colorScheme.primaryContainer
        Priority.Medium -> MaterialTheme.colorScheme.tertiaryContainer
        Priority.High -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.primaryContainer
    }
}

@Composable
fun getPriorityOnContainerColor(priority: Priority?): Color {
    return when (priority) {
        Priority.Low -> MaterialTheme.colorScheme.onPrimaryContainer
        Priority.Medium -> MaterialTheme.colorScheme.onTertiaryContainer
        Priority.High -> MaterialTheme.colorScheme.onErrorContainer
        else -> MaterialTheme.colorScheme.onPrimaryContainer
    }
}