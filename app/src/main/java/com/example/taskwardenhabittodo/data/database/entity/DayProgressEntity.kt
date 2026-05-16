package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo

data class DayProgressEntity(
    @ColumnInfo(name = "dateTimestamp")
    val dateTimestamp: Long,
    @ColumnInfo(name = "totalCount")
    val totalCount: Int,
    @ColumnInfo("completedCount")
    val completedCount: Int
)
