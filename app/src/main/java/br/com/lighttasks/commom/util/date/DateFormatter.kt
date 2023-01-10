package br.com.lighttasks.commom.util.date

import br.com.lighttasks.commom.exceptions.InvalidFormatException
import br.com.lighttasks.commom.extensions.notEquals
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
    private val formatter: SimpleDateFormat = SimpleDateFormat(SERVER_PATTERN, Locale.getDefault())

    fun format(date: String): Date {
        return formatter.parse(date) ?: throw InvalidFormatException("")
    }

    fun format(date: Date): String {
        return formatter.format(date) ?: throw InvalidFormatException("")
    }

    private fun getPattern() = formatter.toPattern()

    fun setPattern(pattern: String) {
        validatePattern(pattern)
        formatter.applyPattern(pattern)
    }

    private fun validatePattern(pattern: String) {
        if ((pattern notEquals SERVER_PATTERN) and (pattern notEquals CLIENT_PATTERN)) {
            throw InvalidFormatException("Date format can only be $SERVER_PATTERN or $CLIENT_PATTERN")
        }
    }


    fun switchPattern() {
        when (getPattern()) {
            CLIENT_PATTERN -> setPattern(SERVER_PATTERN)
            SERVER_PATTERN -> setPattern(CLIENT_PATTERN)
        }
    }

    companion object {
        const val SERVER_PATTERN = "yyyy-MM-dd"
        const val CLIENT_PATTERN = "dd/MM/yyyy"
    }
}

