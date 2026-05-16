package com.example.taskwardenhabittodo.presentation.archive.item

import com.example.taskwardenhabittodo.R
import java.util.Calendar
import java.util.Locale

object TaskDateUtils {

    fun getStartOfToday(): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis
    }

    fun formatArchiveDate(timestamp: Long): DateText {
        val target = Calendar.getInstance().apply { timeInMillis = timestamp }
        val today = Calendar.getInstance().apply { timeInMillis = getStartOfToday() }

        val yesterday = (today.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -1) }
        val dayBeforeYesterday = (today.clone() as Calendar).apply { add(Calendar.DAY_OF_YEAR, -2) }

        return when {
            isSameDay(target, yesterday) -> DateText.Resource(R.string.yesterday)
            isSameDay(target, dayBeforeYesterday) -> DateText.Resource(R.string.day_before_yesterday)
            else -> {
                val monthName = target.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) ?: ""
                val dayOfMonth = target.get(Calendar.DAY_OF_MONTH)
                DateText.Dynamic("$dayOfMonth $monthName")
            }
        }
    }

    private fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

    sealed interface DateText {
        data class Resource(val resId: Int) : DateText
        data class Dynamic(val text: String) : DateText
    }
}