package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateRange
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateRangeStep
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.YearButtonState
import java.time.LocalDate

@Composable
fun CalendarYearGrid(
    gridState: LazyGridState,
    selectedDate: LocalDate,
    startDate: LocalDate,
    onYearSelected: (Int, Int) -> Unit,
    dateRange: DateRange
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(dateRange.step(DateRangeStep.Year()).toList()) { date ->
            val yearButtonState = YearButtonState.getYearState(
                year = date.year,
                selectedYear = selectedDate.year,
                startYear = startDate.year,
            )
            CalendarYear(
                year = date.year,
                yearButtonState = yearButtonState,
                setSelectedYear = { year ->
                    val newPage = dateRange.indexOfFirst {
                        it.year == year && it.month == selectedDate.month
                    }
                    onYearSelected(year, newPage)
                }
            )
        }
    }
}
