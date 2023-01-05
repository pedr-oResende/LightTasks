package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateWrapper
import java.time.LocalDate

@Composable
fun CalendarDay(
    date: DateWrapper,
    onSelected: (LocalDate) -> Unit
) {
    var modifier = Modifier
        .aspectRatio(1F)
        .clip(CircleShape)
    if (!date.isCurrentMonth) {
        Box(modifier = modifier)
        return
    }
    modifier = when {
        date.isSelectedDay -> {
            modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        }
        date.isCurrentDay -> {
            modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        }
        else -> modifier
    }
    if (date.isInDateRange) {
        modifier = modifier.clickable {
            onSelected(date.localDate)
        }
    }
    val textColor = when {
        !date.isInDateRange -> {
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38F)
        }
        date.isSelectedDay -> {
            MaterialTheme.colorScheme.onPrimary
        }
        date.isCurrentDay -> {
            MaterialTheme.colorScheme.primary
        }
        else -> Color.Unspecified
    }
    val text = date.localDate.dayOfMonth.toString()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )
    }
}
