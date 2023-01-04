package br.com.lighttasks.commom.util.date

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {
    companion object {
        private const val SERVER_PATTERN = "yyyy-MM-dd"
        private const val CLIENT_PATTERN = "dd/MM/yyyy"

        private fun getCurrentDate(): String? {
            val dtf = DateTimeFormatter.ofPattern(SERVER_PATTERN)
            return LocalDateTime.now().format(dtf)
        }

        fun daysBetweenDates(second: String?, first: String = getCurrentDate().toString()): Long? {
            if (second == null) return null
            val sdf = SimpleDateFormat(SERVER_PATTERN, Locale.US)
            val firstDate = sdf.parse(first)!!
            val secondDate = sdf.parse(second)!!
            val difference = secondDate.time - firstDate.time
            val seconds = difference / 1000
            val minutes = seconds / 60
            val hours = minutes / 60
            return (hours / 24) + 1
        }

        fun getClientPatternDate(date: String?): String {
            if (date == null) return ""
            val sdf = SimpleDateFormat(SERVER_PATTERN, Locale.US)
            val serverPatterDate = sdf.parse(date) ?: return ""
            sdf.applyPattern(CLIENT_PATTERN)
            val clientPatternDate = sdf.format(serverPatterDate)
            return clientPatternDate.toString()
        }

    }
}