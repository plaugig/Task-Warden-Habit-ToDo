package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day_progress")
data class DayProgressEntity(
    @PrimaryKey
    val dateTimestamp: Long,
    @ColumnInfo(name = "totalTasks")
    val totalTasks: Int,
    @ColumnInfo("completedTasks")
    val completedTasks: Int,
    @ColumnInfo(name = "totalHabits")
    val totalHabits: Int,
    @ColumnInfo(name = "completedHabits")
    val completedHabits: Int
)
