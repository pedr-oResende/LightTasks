package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultFilterChip(
    name: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
) {
    var isSelected by remember { mutableStateOf(false) }
    val contentColor = contentColorFor(color)

    FilterChip(
        onClick = {
            isSelected = !isSelected
            onClick()
        },
        label = {
            Text(text = name)
        },
        shape = RoundedCornerShape(50),
        selected = isSelected,
        colors = FilterChipDefaults.filterChipColors(
            labelColor = color,
            selectedLabelColor = contentColor,
            selectedContainerColor = color
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = color,
            selectedBorderColor = color
        )
    )
}