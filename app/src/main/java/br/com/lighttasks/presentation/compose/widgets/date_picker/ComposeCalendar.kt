package br.com.lighttasks.presentation.compose.widgets.date_picker

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import br.com.lighttasks.R
import br.com.lighttasks.presentation.compose.widgets.date_picker.components.CalendarMainContent
import java.time.LocalDate

@Composable
fun ComposeCalendar(
    startDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.MIN,
    maxDate: LocalDate = LocalDate.MAX,
    onDone: (millis: LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val selectedDate = remember { mutableStateOf(startDate) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDone(selectedDate.value)
            }) {
                Text(text = stringResource(id = R.string.label_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.label_cancel))
            }
        },
        text = {
            CalendarMainContent(
                startDate = startDate,
                minDate = minDate,
                maxDate = maxDate,
                onSelected = {
                    selectedDate.value = it
                }
            )
        }
    )
}