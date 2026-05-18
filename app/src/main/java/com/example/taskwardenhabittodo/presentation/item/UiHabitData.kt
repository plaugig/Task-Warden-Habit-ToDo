package com.example.taskwardenhabittodo.presentation.item

import com.example.taskwardenhabittodo.domain.item.CategoryType

data class UiHabitData(
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: CategoryType,
    val time: String?,
    val isCompleted: Boolean = false,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val colorHex: Long,
    val iconResId: Int
)