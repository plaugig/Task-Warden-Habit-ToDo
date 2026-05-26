package com.example.taskwardenhabittodo.domain.item

import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.HabitEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.HabitData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.data.UserData

// --- Task mappers ---

fun TaskEntity.toDomain(): TaskData {
    return TaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = Priority.valueOf(this.priority),
        time = this.time,
        isCompleted = this.isCompleted,
        isPinned = this.isPinned,
        dayPart = DayPart.valueOf(this.dayPart),
        period = this.period,
        createdAt = this.createdAt,
        colorHex = this.colorHex
    )
}

fun TaskData.toEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority.name,
        time = this.time,
        isCompleted = this.isCompleted,
        isPinned = this.isPinned,
        dayPart = this.dayPart.name,
        period = this.period,
        createdAt = this.createdAt,
        colorHex = this.colorHex
    )
}

fun HabitEntity.toDomain(): HabitData {
    return HabitData(
        id = this.id,
        title = this.title,
        description = this.description,
        category = CategoryType.valueOf(this.category),
        time = this.time,
        isCompleted = this.isCompleted,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        iconResId = this.iconResId,
        createdAt = this.createdAt,
        lastUpdated = this.lastUpdated
    )
}

fun HabitData.toEntity(): HabitEntity {
    return HabitEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        category = this.category.name,
        time = this.time,
        isCompleted = this.isCompleted,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        colorHex = this.colorHex,
        iconResId = this.iconResId,
        createdAt = this.createdAt,
        lastUpdated = this.lastUpdated
    )
}


fun UserEntity.toDomain(): UserData {
    return UserData(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        taskStreak = this.taskStreak,
        habitStreak = this.habitStreak,
        lastHabitStreakCheck = this.lastHabitStreakCheck,
        lastTaskStreakCheck = this.lastTaskStreakCheck
    )
}

fun UserData.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        taskStreak = this.taskStreak,
        habitStreak = this.habitStreak,
        lastHabitStreakCheck = this.lastHabitStreakCheck,
        lastTaskStreakCheck = this.lastTaskStreakCheck
    )
}

fun DayProgressEntity.toDomain(): DayProgressData {
    return DayProgressData(
        dateTimestamp = this.dateTimestamp,
        totalTasks = this.totalTasks,
        completedTasks = this.completedTasks,
        totalHabits = this.totalHabits,
        completedHabits = this.completedHabits
    )
}

fun DayProgressData.toEntity(): DayProgressEntity {
    return DayProgressEntity(
        dateTimestamp = this.dateTimestamp,
        totalTasks = this.totalTasks,
        completedTasks = this.completedTasks,
        totalHabits = this.totalHabits,
        completedHabits = this.completedHabits
    )
}