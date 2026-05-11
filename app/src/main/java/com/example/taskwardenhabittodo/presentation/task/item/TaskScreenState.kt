package com.example.taskwardenhabittodo.presentation.task.item

import com.example.taskwardenhabittodo.ui.UiTaskData

data class TaskScreenState(
    val isLoading: Boolean = true,
    val displayDate: String ="",
    val progress : TaskProgress = TaskProgress(),
    val fireStreak: Int = 0,
    val sections : List<TaskSection> = emptyList(),
    val isBottomSheetVisible: Boolean = false
)

data class TaskProgress(
    val completedCount: Int = 0 ,
    val totalCount: Int = 0 ,
    val percentage: Float = 0f
)

data class TaskSection(
    val title: String,
    val iconRes: Int,
    val tasks : List<UiTaskData>
)