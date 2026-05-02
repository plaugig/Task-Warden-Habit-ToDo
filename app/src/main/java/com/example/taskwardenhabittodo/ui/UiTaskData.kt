package com.example.taskwardenhabittodo.ui

import com.example.taskwardenhabittodo.domain.CategoryType
import com.example.taskwardenhabittodo.domain.DayPart
import com.example.taskwardenhabittodo.domain.Priority

data class UiTaskData (
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val category: CategoryType,
    val time: String,
    val period: String,
    val dayPart: DayPart,
    val isCompleted: Boolean = false,
    val isHabit: Boolean = false,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val colorHex: Long,
    val isPinned: Boolean,
    val iconResId: Int,

)