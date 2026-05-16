package com.example.taskwardenhabittodo.domain.item.data

data class DayProgressData(
    val dateTimestamp: Long,
    val totalCount: Int,
    val completedCount: Int
)