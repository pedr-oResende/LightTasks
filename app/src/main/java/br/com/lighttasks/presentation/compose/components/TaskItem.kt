package br.com.lighttasks.presentation.compose.components

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
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = getPriorityContainerColor(priority = task.priority)
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
                    color = getPriorityOnContainerColor(priority = task.priority)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = task.description ?: "",
                    color = getPriorityOnContainerColor(priority = task.priority),
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