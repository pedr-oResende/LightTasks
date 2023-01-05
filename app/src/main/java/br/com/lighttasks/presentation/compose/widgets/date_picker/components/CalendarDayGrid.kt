package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.lighttasks.presentation.compose.widgets.date_picker.model.DateRange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarDayGrid(
    totalPageCount: Int,
    pagerState: PagerState,
    initialPage: Int,
    startDate: LocalDate,
    dateRange: DateRange,
    selectedDate: LocalDate,
    setSelectedDate: (LocalDate) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        DayOfWeek.values().forEach {
            Text(
                modifier = Modifier.weight(1f),
                text = it.getDisplayName(
                    TextStyle.NARROW,
                    Locale.getDefault()
                ),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    HorizontalPager(
        count = totalPageCount,
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        val pageDiff = page.minus(initialPage).absoluteValue.toLong()
        val date = if (page > initialPage) {
            startDate.plusMonths(pageDiff)
        } else if (page < initialPage) {
            startDate.minusMonths(pageDiff)
        } else {
            startDate
        }
        CalendarGrid(
            pagerDate = date.withDayOfMonth(1),
            dateRange = dateRange,
            selectedDate = selectedDate,
            onSelected = setSelectedDate
        )
    }
}