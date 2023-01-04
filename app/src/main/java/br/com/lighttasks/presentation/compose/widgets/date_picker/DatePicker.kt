package br.com.lighttasks.presentation.compose.widgets.date_picker

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.lighttasks.commom.util.date.DateUtils
import java.util.*

@Composable
fun DatePicker(
    showDatePicker: Boolean,
    setShowDatePicker: (Boolean) -> Unit,
    datePickerResultListener: (String) -> Unit
) {
    if (showDatePicker) {
        val context = LocalContext.current
        val year: Int
        val month: Int
        val day: Int
        val calendar = Calendar.getInstance()

        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.time = Date()

        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                val cal = Calendar.getInstance()
                cal.set(mYear, mMonth + 1, mDayOfMonth)
                val dateString = DateUtils.getString(cal, "yyyy-MM-dd")
                datePickerResultListener(dateString ?: "")
                setShowDatePicker(false)
            }, year, month, day
        )
        datePickerDialog.show()
    }
}