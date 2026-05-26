package com.example.taskwardenhabittodo.presentation.task.today.item

import com.example.taskwardenhabittodo.presentation.item.UiTaskData

data class TaskScreenState(
    val isLoading: Boolean = true,
    val displayDate: String ="",
    val progress : TaskProgress = TaskProgress(),
    val taskStreak: Int = 0,
    val sections : List<TaskSection> = emptyList(),
    val isBottomSheetVisible: Boolean = false,
    val selectedTaskForEdit: UiTaskData? = null
)

data class TaskProgress(
    val completedCount: Int = 0 ,
    val totalCount: Int = 0 ,
    val percentage: Float = 0f
)

data class TaskSection(
    val title: Int,
    val iconRes: Int,
    val tasks : List<UiTaskData>
)