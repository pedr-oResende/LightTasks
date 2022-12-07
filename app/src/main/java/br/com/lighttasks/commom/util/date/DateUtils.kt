package br.com.lighttasks.commom.util.date

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {
    companion object {
        private fun getCurrentDate(): String? {
            val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return LocalDateTime.now().format(dtf)
        }

        fun daysBetweenDates(second: String?, first: String = getCurrentDate().toString()): Long? {
            if (second == null) return null
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val firstDate = sdf.parse(first)!!
            val secondDate = sdf.parse(second)!!
            val difference = secondDate.time - firstDate.time
            val seconds = difference / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            return (hours / 24) + 1
        }
    }
}