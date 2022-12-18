package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.lighttasks.commom.util.priority.getPriorityContainerColor
import br.com.lighttasks.commom.util.priority.getPriorityOnContainerColor
import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .border(
                width = 2.dp,
                color = getPriorityContainerColor(priority = task.priority),
                shape = MaterialTheme.shapes.medium
            ),

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = task.name ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.description ?: "",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row {
                if (task.priority == Priority.High) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        tint = MaterialTheme.colorScheme.error,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    tint = getPriorityOnContainerColor(priority = task.priority),
                    contentDescription = null
                )
            }

        }

    }
}