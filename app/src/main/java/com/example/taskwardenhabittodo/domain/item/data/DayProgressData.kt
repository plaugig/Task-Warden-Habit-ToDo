package com.example.taskwardenhabittodo.domain.item.data

data class DayProgressData(
    val dateTimestamp: Long,
    val totalTasks: Int,
    val completedTasks: Int,
    val totalHabits: Int,
    val completedHabits: Int
)