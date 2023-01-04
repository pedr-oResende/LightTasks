package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CalendarYear(
    year: Int,
    isSelectedYear: Boolean,
    isCurrentYear: Boolean,
    setSelectedYear: (Int) -> Unit
) {
    if (isSelectedYear) {
        Button(onClick = {
            setSelectedYear(year)
        }) {
            Text("$year", maxLines = 1)
        }
    } else if (isCurrentYear) {
        OutlinedButton(onClick = {
            setSelectedYear(year)
        }) {
            Text("$year", maxLines = 1)
        }
    } else {
        TextButton(onClick = { setSelectedYear(year) }) {
            Text("$year", maxLines = 1)
        }
    }
}