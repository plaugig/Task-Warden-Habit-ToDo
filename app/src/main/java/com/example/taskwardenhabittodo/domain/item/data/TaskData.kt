package com.example.taskwardenhabittodo.domain.item.data

import androidx.room.ColumnInfo
import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.domain.item.Priority

data class TaskData(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val time: String?,
    val period: String,
    val dayPart: DayPart,
    val isCompleted: Boolean = false,
    val isPinned: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val colorHex: Long
)