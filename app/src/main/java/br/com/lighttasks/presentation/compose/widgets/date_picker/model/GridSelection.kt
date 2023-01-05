package br.com.lighttasks.presentation.compose.widgets.date_picker.model

enum class GridSelection {
    Year, Day;
}

fun GridSelection.switch(): GridSelection {
    return when (this) {
        GridSelection.Year -> GridSelection.Day
        GridSelection.Day -> GridSelection.Year
    }
}