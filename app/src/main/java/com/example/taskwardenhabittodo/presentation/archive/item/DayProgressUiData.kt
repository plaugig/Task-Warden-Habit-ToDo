package com.example.taskwardenhabittodo.presentation.archive.item

data class DayProgressUiData(
    val dateTimestamp: Long,
    val totalCount: Int,
    val completedCount: Int,
    val displayDate: TaskDateUtils.DateText,
    val progress: Float
)
