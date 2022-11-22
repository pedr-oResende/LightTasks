package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.lighttasks.commom.util.priority.getPriorityContainerColor
import br.com.lighttasks.commom.util.priority.getPriorityOnContainerColor
import br.com.lighttasks.domain.model.Priority
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.domain.model.tasks
import br.com.lighttasks.presentation.compose.theme.LightTasksTheme

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isSystemInDarkTheme())
                    MaterialTheme.colorScheme.background
                else
                    getPriorityContainerColor(priority = task.priority)
            )
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
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.description ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                tint = getPriorityOnContainerColor(priority = task.priority),
                contentDescription = null
            )
        }

    }
}