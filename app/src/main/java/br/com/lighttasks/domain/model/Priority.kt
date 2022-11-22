package br.com.lighttasks.domain.model

import br.com.lighttasks.commom.util.date.DateUtils


enum class Priority {
    Low, Medium, High;

    companion object {
        fun getPriority(deadline: String?): Priority {
            val daysBetween = DateUtils.daysBetweenDates(deadline) ?: return Low
            return when {
                daysBetween <= 1 -> High
                daysBetween in 2..5 -> Medium
                else -> Low
            }
        }
    }
}