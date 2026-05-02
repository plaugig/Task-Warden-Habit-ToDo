package com.example.taskwardenhabittodo.ui.presentation.main.screen

import com.example.taskwardenhabittodo.ui.DayProgress
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.UiUserData

data class MainScreenState (
    val isLoading: Boolean = true,

    val user: UiUserData = UiUserData(
        petPoints = 0,
        dailyPoints = 0,
        fireStreak = 0,
        masteryStreak = 0),
    val progress: DayProgress = DayProgress(
        totalTasks = 0,
        completedTasks = 0,
        totalHabits = 0,
        completedHabits = 0),
    val todayHabits: List<UiTaskData> = emptyList(),

    val focusTask: UiTaskData? = null

)