package com.example.taskwardenhabittodo.presentation.item

import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.HabitData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.presentation.archive.item.DayProgressUiData
import com.example.taskwardenhabittodo.presentation.archive.item.TaskDateUtils

fun TaskData.toUi(): UiTaskData {
    return UiTaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        time = this.time,
        period = this.period,
        dayPart = this.dayPart,
        isCompleted = this.isCompleted,
        isPinned = this.isPinned,
        colorHex = this.colorHex
    )
}

fun UiTaskData.toDomain(): TaskData {
    return TaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        time = this.time,
        period = this.period,
        dayPart = this.dayPart,
        isCompleted = this.isCompleted,
        isPinned = this.isPinned,
        createdAt = System.currentTimeMillis(),
        colorHex = this.colorHex
    )
}


fun HabitData.toUi(): UiHabitData {
    return UiHabitData(
        id = this.id,
        title = this.title,
        description = this.description,
        category = this.category,
        time = this.time,
        isCompleted = this.isCompleted,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        iconResId = this.iconResId
    )
}

fun UiHabitData.toDomain(): HabitData {
    return HabitData(
        id = this.id,
        title = this.title,
        description = this.description,
        category = this.category,
        time = this.time,
        isCompleted = this.isCompleted,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        iconResId = this.iconResId,
        createdAt = System.currentTimeMillis(),
        lastUpdated = System.currentTimeMillis()
    )
}


fun UserData.toUi(): UiUserData {
    return UiUserData(
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        taskStreak = this.taskStreak,
        habitStreak = this.habitStreak
    )
}


fun DayProgressData.toUi(): DayProgressUiData {
    val calculatedProgress = if (this.totalTasks > 0) {
        this.completedTasks.toFloat() / this.totalTasks.toFloat()
    } else {
        0f
    }

    return DayProgressUiData(
        dateTimestamp = this.dateTimestamp,
        totalCount = this.totalTasks,
        completedCount = this.completedTasks,
        displayDate = TaskDateUtils.formatArchiveDate(this.dateTimestamp),
        progress = calculatedProgress
    )
}

