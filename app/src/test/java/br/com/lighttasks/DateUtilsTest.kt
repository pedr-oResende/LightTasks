package br.com.lighttasks

import br.com.lighttasks.commom.extensions.equals
import br.com.lighttasks.commom.util.date.DateUtils
import org.junit.Test
import java.util.Date

class DateUtilsTest {

    @Test
    fun testDaysBetweenDates() {
        val first = "2023-01-09"
        val second = "2023-01-12"
        val days = DateUtils.daysBetweenDates(first = first, second = second)
        assert(days == 3L)
    }

    @Test
    fun testGetClientPatterDate() {
        val date = DateUtils.getClientPatternDate("2023-01-09")
        assert(date equals "09/01/2023")
    }

    @Test
    fun testGetServerPatterDate() {
        val date = DateUtils.getServerPatternDate("12/01/2023")
        assert(date equals "2023-01-12")
    }

}