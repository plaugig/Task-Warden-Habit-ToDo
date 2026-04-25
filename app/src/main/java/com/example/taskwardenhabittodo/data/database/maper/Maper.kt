package com.example.taskwardenhabittodo.data.database.maper

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity

fun TaskEntity.toDomain(): TaskData {
    return TaskData(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        time = this.time,
        isCompleted = this.isCompleted,
        isHabit = this.isHabit,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        category = this.category,
        colorHex = this.colorHex,
        isPinned = this.isPinned
    )
}

fun TaskData.toEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        priority = this.priority,
        time = this.time,
        isCompleted = this.isCompleted,
        isHabit = this.isHabit,
        targetCount = this.targetCount,
        currentCount = this.currentCount,
        category = this.category,
        colorHex = this.colorHex,
        isPinned = this.isPinned
    )
}


fun UserEntity.toDomain(tasks: List<TaskEntity>): UserData {
    return UserData(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        fireStreak = this.fireStreak,
        masteredCount = this.masteredCount,
        totalTasks = tasks.size,
        completedTasks = tasks.count { it.isCompleted },
        targetMastery = 5
    )
}

fun UserData.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        petPoints = this.petPoints,
        dailyPoints = this.dailyPoints,
        fireStreak = this.fireStreak,
        masteredCount = this.masteredCount
    )
}