package br.com.lighttasks.presentation.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedSmallButton(
    name: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val isSelected = remember { mutableStateOf(false) }
    val contentColor = contentColorFor(color)
    Button(
        onClick = {
            isSelected.value = !isSelected.value
            onClick()
        },
        colors = if (isSelected.value)
            ButtonDefaults.outlinedButtonColors(
                contentColor = color,
                disabledContentColor = color
            )
        else
            ButtonDefaults.buttonColors(
                containerColor = color,
                disabledContainerColor = color,
                contentColor = contentColor,
                disabledContentColor = contentColor
            ),
        border = if (isSelected.value)
            BorderStroke(
            width = 2.dp,
            color = color
        ) else null,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}