package br.com.lighttasks.presentation.compose.components.task

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import br.com.lighttasks.domain.model.Task

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    tasks: List<Task>,
    onItemClick: (Task) -> Unit,
    onItemLongClick: (Task) -> Unit
) {
    LazyColumn {
        item { Spacer(modifier = Modifier.height(8.dp)) }
        items(tasks) { task ->
            TaskItem(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
                    .combinedClickable(
                        onClick = { onItemClick(task) },
                        onLongClick = { onItemLongClick(task) }
                    ),
                task = task
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
    }
}