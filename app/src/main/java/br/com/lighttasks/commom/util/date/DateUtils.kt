package br.com.lighttasks.commom.util.date

import java.text.SimpleDateFormat
import java.time.LocalDate
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
            if (date.isNullOrBlank()) return ""
            val sdf = SimpleDateFormat(SERVER_PATTERN, Locale.US)
            val serverPatterDate = sdf.parse(date) ?: return ""
            sdf.applyPattern(CLIENT_PATTERN)
            val clientPatternDate = sdf.format(serverPatterDate)
            return clientPatternDate.toString()
        }

        fun getServerPatternDate(date: String?): String {
            if (date.isNullOrBlank()) return ""
            val sdf = SimpleDateFormat(CLIENT_PATTERN, Locale.US)
            val clientPatternDate = sdf.parse(date) ?: return ""
            sdf.applyPattern(SERVER_PATTERN)
            val serverPatterDate = sdf.format(clientPatternDate)
            return serverPatterDate.toString()
        }

        fun getString(date: Long, format: String?): String? {
            val d = Date(date)
            return getString(d, format)
        }

        fun getString(date: Date?, format: String?): String? {
            val sdf = SimpleDateFormat(format, Locale("pt", "Br"))
            return sdf.format(date)
        }

        fun getString(date: Calendar?, format: String?): String? {
            return if (date == null) {
                ""
            } else getString(date.time, format)
        }

        fun getServerPatternDate(date: String?): String {
            if (date.isNullOrBlank()) return ""
            val sdf = SimpleDateFormat(CLIENT_PATTERN, Locale.US)
            val clientPatternDate = sdf.parse(date) ?: return ""
            sdf.applyPattern(SERVER_PATTERN)
            val serverPatterDate = sdf.format(clientPatternDate)
            return serverPatterDate.toString()
        }

        fun getString(date: Long, format: String?): String? {
            val d = Date(date)
            return getString(d, format)
        }

        fun getString(date: Date?, format: String?): String? {
            val sdf = SimpleDateFormat(format, Locale("pt", "Br"))
            return sdf.format(date)
        }

        fun getString(date: Calendar?, format: String?): String? {
            return if (date == null) {
                ""
            } else getString(date.time, format)
        }

        fun getLocalDate(date: String): LocalDate {
            if (date.isBlank()) return LocalDate.now()
            val sdf = SimpleDateFormat(SERVER_PATTERN, Locale("pt", "Br"))
            val formattedDate = sdf.parse(date) ?: return LocalDate.now()
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