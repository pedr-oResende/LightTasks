package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateRange
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateRangeStep
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateWrapper
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.rangeTo
import java.time.LocalDate

@Composable
fun CalendarGrid(
    pagerDate: LocalDate,
    dateRange: DateRange,
    selectedDate: LocalDate,
    onSelected: (LocalDate) -> Unit
) {
    val gridSpacing = 4.dp
    val today = LocalDate.now()
    val firstWeekDayOfMonth = pagerDate.dayOfWeek
    val pagerMonth = pagerDate.month
    val gridStartDay = pagerDate
        .minusDays(firstWeekDayOfMonth.value.toLong() - 1)
    val gridEndDay = gridStartDay.plusDays(41)

    val dates = (gridStartDay.rangeTo(gridEndDay) step DateRangeStep.Day()).map {
        DateWrapper(
            localDate = it,
            isSelectedDay = it == selectedDate,
            isCurrentDay = it == today,
            isCurrentMonth = it.month == pagerMonth,
            isInDateRange = it in dateRange
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing),
        verticalArrangement = Arrangement.spacedBy(gridSpacing)
    ) {
        items(dates) {
            CalendarDay(
                date = it,
                onSelected = onSelected
            )
        }
    }

}