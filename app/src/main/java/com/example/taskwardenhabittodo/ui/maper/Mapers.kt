package com.example.taskwardenhabittodo.ui.maper

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.UserData
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
        isHabit = this.isHabit,
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
        isHabit = this.isHabit,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        isPinned = this.isPinned,
        iconResId = this.iconResId
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

