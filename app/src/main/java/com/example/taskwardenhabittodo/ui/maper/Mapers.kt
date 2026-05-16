package com.example.taskwardenhabittodo.ui.maper

import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.presentation.archive.item.DayProgressUiData
import com.example.taskwardenhabittodo.presentation.archive.item.TaskDateUtils
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.UiUserData

fun TaskData.toUi(): UiTaskData {
    return UiTaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        category = this.category,
        time = this.time,
        period = this.period,
        dayPart = this.dayPart,
        isCompleted = this.isCompleted,
        classification = this.classification,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        isPinned = this.isPinned,
        iconResId = this.iconResId

    )
}

fun UiTaskData.toDomain(): TaskData{
    return TaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        category = this.category,
        time = this.time,
        period = this.period,
        dayPart = this.dayPart,
        isCompleted = this.isCompleted,
        classification = this.classification,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        isPinned = this.isPinned,
        iconResId = this.iconResId,
        lastUpdated = System.currentTimeMillis()
    )
}

fun UserData.toUi(): UiUserData {
    return UiUserData(
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        fireStreak = this.fireStreak,
        masteryStreak = this.masteryStreak,

    )
}


fun DayProgressData.toUi(): DayProgressUiData{
    val calculatedProgress = if (this.totalCount > 0) {
        this.completedCount.toFloat() / this.totalCount.toFloat()
    } else {
        0f
    }

    return DayProgressUiData(
        dateTimestamp = this.dateTimestamp,
        totalCount = this.totalCount,
        completedCount = this.completedCount,
        displayDate = TaskDateUtils.formatArchiveDate(this.dateTimestamp),
        progress = calculatedProgress
    )
}

