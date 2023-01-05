package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.*
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.rangeTo
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.ranges.coerceAtLeast
import kotlin.ranges.coerceAtMost

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarMainContent(
    startDate: LocalDate,
    minDate: LocalDate,
    maxDate: LocalDate,
    onSelected: (LocalDate) -> Unit,
) {
    val dateRange = getDateRange(minDate, maxDate)
    val totalPageCount = dateRange.count()
    val initialPage = getStartPage(startDate, dateRange, totalPageCount)

    var gridSelection by rememberSaveable { mutableStateOf(GridSelection.Day) }
    var currentPagerDate by rememberSaveable { mutableStateOf(startDate.withDayOfMonth(1)) }
    var selectedDate by rememberSaveable { mutableStateOf(startDate) }

    val pagerState = rememberPagerState(initialPage)
    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()

    val setSelectedDate: (LocalDate) -> Unit = {
        onSelected(it)
        selectedDate = it
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            val pageDiff = page.minus(initialPage).absoluteValue.toLong()
            val date = if (page > initialPage) {
                startDate.plusMonths(pageDiff)
            } else if (page < initialPage) {
                startDate.minusMonths(pageDiff)
            } else {
                startDate
            }
            currentPagerDate = date
        }
    }

    Column(
        modifier = Modifier.height(440.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CalendarTopBar(selectedDate)
        CalendarMonthYearSelector(
            currentPage = pagerState.currentPage,
            pagerDate = currentPagerDate,
            pageCount = pagerState.pageCount,
            onChipClicked = {
                gridSelection = gridSelection.switch()
            },
            onPageChanged = { page ->
                coroutineScope.launch {
                    pagerState.scrollToPage(page)
                }
            }
        )
        when (gridSelection) {
            GridSelection.Year -> {
                CalendarYearGrid(
                    gridState = gridState,
                    dateRange = dateRange,
                    selectedDate = selectedDate,
                    startDate = startDate,
                    onYearSelected = { year, page ->
                        coroutineScope.launch {
                            setSelectedDate(selectedDate.withYear(year))
                            currentPagerDate = currentPagerDate.withYear(year)
                            gridSelection = GridSelection.Day
                            pagerState.scrollToPage(page)
                        }
                    }
                )
            }
            GridSelection.Day -> {
                CalendarDayGrid(
                    totalPageCount = totalPageCount,
                    pagerState = pagerState,
                    initialPage = initialPage,
                    startDate = startDate,
                    dateRange = dateRange,
                    selectedDate = selectedDate,
                    setSelectedDate = setSelectedDate
                )
            }
        }
    }
}

private fun getStartPage(
    startDate: LocalDate,
    dateRange: DateRange,
    pageCount: Int
): Int {
    if (startDate <= dateRange.start) {
        return 0
    }
    if (startDate >= dateRange.endInclusive) {
        return pageCount
    }
    val indexOfRange = dateRange.indexOf(startDate.withDayOfMonth(1))
    return if (indexOfRange != -1) indexOfRange else pageCount / 2
}

private fun getDateRange(min: LocalDate, max: LocalDate): DateRange {
    val lowerBound = with(min) {
        val year = with(LocalDate.now().minusYears(100).year) {
            100.0 * (floor(abs(this / 100.0)))
        }
        coerceAtLeast(
            LocalDate.now().withYear(year.toInt()).withDayOfYear(1)
        )
    }
    val upperBound = with(max) {
        val year = with(LocalDate.now().year) {
            100.0 * (ceil(abs(this / 100.0)))
        }
        coerceAtMost(LocalDate.now().withYear(year.toInt())).apply {
            withDayOfYear(this.lengthOfYear())
        }
    }
    return lowerBound.rangeTo(upperBound) step DateRangeStep.Month()
}