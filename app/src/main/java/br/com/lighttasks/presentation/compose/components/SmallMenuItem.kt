package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SmallMenuItem(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val isSelected = remember { mutableStateOf(false) }
    val containerColor = color.copy(alpha = if (isSelected.value) 0.6f else 1f)
    val contentColor = contentColorFor(containerColor).copy(alpha = if (isSelected.value) 0.6f else 1f)
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(50))
            .clickable {
                isSelected.value = !isSelected.value
                onClick()
            },
        tonalElevation = if (isSelected.value) 8.dp else 0.dp,
        color = containerColor,
        contentColor = contentColor
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}