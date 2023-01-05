package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.YearButtonState

@Composable
fun CalendarYear(
    year: Int,
    yearButtonState: YearButtonState,
    setSelectedYear: (Int) -> Unit
) {
    when (yearButtonState) {
        YearButtonState.IsSelected -> {
            Button(onClick = { setSelectedYear(year) }) {
                Text("$year", maxLines = 1)
            }
        }
        YearButtonState.IsCurrent -> {
            OutlinedButton(onClick = { setSelectedYear(year) }) {
                Text("$year", maxLines = 1)
            }
        }
        YearButtonState.IsNotSelected -> {
            TextButton(onClick = { setSelectedYear(year) }) {
                Text("$year", maxLines = 1)
            }
        }
    }
}