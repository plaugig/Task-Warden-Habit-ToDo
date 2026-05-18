package com.example.taskwardenhabittodo.presentation.task.today.item

import com.example.taskwardenhabittodo.domain.item.DayPart
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

object TodayTaskDateUtils {
    fun determineDayPart(time: String): DayPart {
        val hour = time.split(":").firstOrNull()?.toIntOrNull() ?: 12

        return when (hour) {
            in 4..11 -> DayPart.MORNING
            in 12..17 -> DayPart.AFTERNOON
            in 18..23, in 0..3 -> DayPart.EVENING
            else -> DayPart.MORNING
        }
    }

    fun formatDisplayDate(epochMillis: Long): String {
        val date = Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault())

        return date.format(formatter)
    }

    fun getEndOfDay(): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.timeInMillis
    }
}