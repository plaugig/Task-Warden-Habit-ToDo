package com.example.taskwardenhabittodo.presentation.tasks.history.item

import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.presentation.item.UiTaskData

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object HistoryDayTaskDateUtils {
    fun formatArchiveDisplayDate(epochMillis: Long): String {
        val date = Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

        val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault())
        return date.format(formatter)
    }

    fun prepareArchiveSections(tasks: List<UiTaskData>): List<HistoryTaskSection> {
        return listOf(
            HistoryTaskSection(
                titleRes = R.string.task_section_morning,
                iconRes = R.drawable.ev_sun,
                tasks = tasks.filter { it.dayPart == DayPart.MORNING }
            ),
            HistoryTaskSection(
                titleRes = R.string.task_section_afternoon,
                iconRes = R.drawable.sun,
                tasks = tasks.filter { it.dayPart == DayPart.AFTERNOON }
            ),
            HistoryTaskSection(
                titleRes = R.string.task_section_evening,
                iconRes = R.drawable.moon,
                tasks = tasks.filter { it.dayPart == DayPart.EVENING }
            ),
            HistoryTaskSection(
                titleRes = R.string.task_section_other,
                iconRes = R.drawable.animal,
                tasks = tasks.filter { it.dayPart == DayPart.ALL_DAY }
            )
        ).filter { it.tasks.isNotEmpty() }
    }

    fun calculateProgress(tasks: List<UiTaskData>): HistoryProgress {
        val completed = tasks.count { it.isCompleted }
        val total = tasks.size

        return HistoryProgress(
            completedCount = completed,
            totalCount = total,
            percentage = if (total > 0) completed.toFloat() / total else 0f
        )
    }
}