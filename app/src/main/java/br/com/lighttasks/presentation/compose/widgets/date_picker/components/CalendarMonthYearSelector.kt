package br.com.lighttasks.presentation.compose.widgets.date_picker.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.OffsetTime
import java.util.*

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalendarMonthYearSelector(
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    pagerDate: LocalDate,
    onChipClicked: () -> Unit
) {
    val pagerMonthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            label = {
                Text(
                    pagerMonthFormat.format(
                        Date.from(pagerDate.atTime(OffsetTime.now()).toInstant())
                    )
                )
            },
            selected = false,
            border = null,
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, "ArrowDropDown")
            },
            onClick = onChipClicked,
        )
        Spacer(modifier = Modifier.weight(1F))
        IconButton(
            onClick = {
                coroutineScope.launch {
                    with(pagerState) {
                        try {
                            animateScrollToPage(currentPage - 1)
                        } catch (e: Exception) {
                            // avoid IOOB and animation crashes
                        }
                    }
                }
            }
        ) {
            Icon(
                Icons.Default.ChevronLeft, "ChevronLeft"
            )
        }
        IconButton(
            onClick = {
                coroutineScope.launch {
                    with(pagerState) {
                        try {
                            animateScrollToPage(currentPage + 1)
                        } catch (e: Exception) {
                            // avoid IOOB and animation crashes
                        }
                    }
                }
            }
        ) {
            Icon(
                Icons.Default.ChevronRight, "ChevronRight",
            )
        }
    }
}