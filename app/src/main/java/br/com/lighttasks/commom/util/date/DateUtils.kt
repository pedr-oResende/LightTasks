package br.com.lighttasks.commom.util.date

import br.com.lighttasks.commom.extensions.ifNull
import br.com.lighttasks.commom.util.date.DateFormatter.Companion.CLIENT_PATTERN
import br.com.lighttasks.commom.util.date.DateFormatter.Companion.SERVER_PATTERN
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class DateUtils {
    companion object {
        private val formatter = DateFormatter()

        private fun getCurrentDate(): String {
            val dtf = DateTimeFormatter.ofPattern(SERVER_PATTERN)
            return LocalDateTime.now().format(dtf)
        }

        fun daysBetweenDates(second: String?, first: String = getCurrentDate()): Long? {
            if (second == null) return null
            formatter.setPattern(SERVER_PATTERN)
            val firstDate = formatter.format(first)
            val secondDate = formatter.format(second)
            val difference = secondDate.time - firstDate.time
            return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
        }

        fun getClientPatternDate(date: String): String {
            return try {
                getFormattedDate(currentPattern = SERVER_PATTERN, date)
            } catch (e: Exception) {
                ""
            }
        }

        fun getServerPatternDate(date: String): String {
            return try {
                getFormattedDate(currentPattern = CLIENT_PATTERN, date)
            } catch (e: Exception) {
                ""
            }
        }

        private fun getFormattedDate(currentPattern: String, date: String): String {
            val currentPatterDate = getCurrentPatternDate(currentPattern, date)
            formatter.switchPattern()
            val targetPatternDate = formatter.format(currentPatterDate)
            return targetPatternDate ifNull ""
        }

        private fun getCurrentPatternDate(currentPattern: String, date: String): Date {
            formatter.setPattern(currentPattern)
            return formatter.format(date)
        }

        fun getString(date: Date, format: String): String {
            formatter.setPattern(format)
            return formatter.format(date)
        }

        fun getLocalDate(date: String): LocalDate {
            formatter.setPattern(SERVER_PATTERN)
            val formattedDate = formatter.format(date)
            val calendar = Calendar.getInstance()
            calendar.time = formattedDate
            return LocalDate.of(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }

    }
}