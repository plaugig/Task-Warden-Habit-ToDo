package com.example.taskwardenhabittodo.presentation.item

import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.domain.item.Priority
import com.example.taskwardenhabittodo.ui.theme.HabitColorPurple

data class UiTaskData(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val time: String?,
    val period: String,
    val dayPart: DayPart,
    val isCompleted: Boolean = false,
    val isPinned: Boolean = false,
    val colorHex: Long
)