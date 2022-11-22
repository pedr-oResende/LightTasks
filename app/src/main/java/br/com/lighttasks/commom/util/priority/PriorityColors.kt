package br.com.lighttasks.commom.util.priority

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.lighttasks.domain.model.Priority

@Composable
fun getPriorityContainerColor(priority: Priority): Color {
    return when (priority) {
        Priority.Low -> MaterialTheme.colorScheme.tertiaryContainer
        Priority.Medium -> MaterialTheme.colorScheme.primaryContainer
        Priority.High -> MaterialTheme.colorScheme.errorContainer
    }
}

@Composable
fun getPriorityOnContainerColor(priority: Priority): Color {
    return when (priority) {
        Priority.Low -> MaterialTheme.colorScheme.onTertiaryContainer
        Priority.Medium -> MaterialTheme.colorScheme.onPrimaryContainer
        Priority.High -> MaterialTheme.colorScheme.onErrorContainer
    }
}