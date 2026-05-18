package com.example.taskwardenhabittodo.presentation.tasks.history.item

import androidx.annotation.StringRes
import com.example.taskwardenhabittodo.presentation.item.UiTaskData

data class HistoryDayTaskScreenState(
    val isLoading: Boolean = true,
    val displayDate: String = "",
    val progress: HistoryProgress = HistoryProgress(),
    val sections: List<HistoryTaskSection> = emptyList()
)

data class HistoryProgress(
    val completedCount: Int = 0,
    val totalCount: Int = 0,
    val percentage: Float = 0f
)

data class HistoryTaskSection(
    @StringRes val titleRes: Int,
    val iconRes: Int,
    val tasks: List<UiTaskData>
)