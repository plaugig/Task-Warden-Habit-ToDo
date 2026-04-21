package com.example.taskwardenhabittodo.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TaskData (
    val id : Int = 0,
    val title: String,
    val description: String,
    val priority: String,
    val time: String,
    val isCompleted : Boolean = false,
    val isHabit: Boolean = false,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)