package com.example.taskwardenhabittodo.ui

data class UiTaskData (
    val id : Int = 0,
    val title: String,
    val description: String,
    val priority: String,
    val time: String,
    val isCompleted : Boolean = false,
    val isHabit: Boolean = false,
    val targetCount: Int = 1,
    val currentCount: Int = 0,
    val category: String,
    val colorHex: Long,
    val isPinned: Boolean
)