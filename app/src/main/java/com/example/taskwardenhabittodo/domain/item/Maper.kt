package com.example.taskwardenhabittodo.domain.item

import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.data.UserData

fun TaskEntity.toDomain(): TaskData {
    return TaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = Priority.valueOf(this.priority),
        time = this.time,
        classification = this.classification,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        category = CategoryType.valueOf(this.category),
        colorHex = this.colorHex,
        isPinned = this.isPinned,
        dayPart = DayPart.valueOf(this.dayPart),
        period = period,
        createdAt = createdAt,
        iconResId = this.iconResId,
        isCompleted = this.isCompleted,
        lastUpdated = this.lastUpdated
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
        classification = this.classification,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        category = this.category.name,
        colorHex = this.colorHex,
        isPinned = this.isPinned,
        dayPart = this.dayPart.name,
        period = period,
        createdAt = createdAt,
        iconResId = this.iconResId,
        lastUpdated = this.lastUpdated
    )
}


fun UserEntity.toDomain(): UserData {
    return UserData(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        fireStreak = this.fireStreak,
        masteryStreak = this.masteryStreak
    )
}

fun UserData.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        fireStreak = this.fireStreak,
        masteryStreak = this.masteryStreak
    )
}

fun DayProgressEntity.toDomain(): DayProgressData{
    return DayProgressData(
        dateTimestamp = this.dateTimestamp,
        totalCount = this.totalCount,
        completedCount = this.completedCount
    )
}