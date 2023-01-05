package br.com.lighttasks.presentation.compose.widgets.date_picker

import androidx.compose.runtime.Composable
import br.com.lighttasks.commom.util.date.DateUtils
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Composable
fun DefaultDatePicker(
    startDate: LocalDate = LocalDate.now(),
    showDatePicker: Boolean,
    setShowDatePicker: (Boolean) -> Unit,
    datePickerResultListener: (String) -> Unit
) {
    if (showDatePicker) {
        ComposeCalendar(
            minDate = LocalDate.now(),
            maxDate = LocalDate.now().plusYears(1L),
            startDate = startDate,
            onDone = {
                val zoneId = ZoneId.systemDefault()
                val date = Date.from(it.atStartOfDay(zoneId).toInstant())
                val dateString = DateUtils.getString(date, "yyyy-MM-dd")
                datePickerResultListener(dateString ?: "")
                setShowDatePicker(false)
            },
            onDismiss = {
                setShowDatePicker(false)
            }
        )
    }
}