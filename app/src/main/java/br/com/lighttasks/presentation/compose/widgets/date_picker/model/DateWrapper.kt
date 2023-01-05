package br.com.lighttasks.presentation.compose.widgets.date_picker.model

import java.time.LocalDate

data class DateWrapper(
    val localDate: LocalDate,
    val isSelectedDay: Boolean,
    val isCurrentDay: Boolean,
    val isCurrentMonth: Boolean,
    val isInDateRange: Boolean
)
