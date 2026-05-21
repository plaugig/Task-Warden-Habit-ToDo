package com.example.taskwardenhabittodo.presentation.habit.item

import com.example.taskwardenhabittodo.presentation.item.DayProgress
import com.example.taskwardenhabittodo.presentation.item.UiHabitData
import com.example.taskwardenhabittodo.presentation.item.UiTaskData
import com.example.taskwardenhabittodo.presentation.item.UiUserData

data class HabitScreenState(
    val isLoading: Boolean = true,
    val user: UiUserData = UiUserData(
        petPoints = 0,
        dailyPoints = 0,
        taskStreak = 0,
        habitStreak = 0
    ),
    val progress: DayProgress = DayProgress(
        totalTasks = 0,
        completedTasks = 0,
        totalHabits = 0,
        completedHabits = 0
    ),
    val todayHabits: List<UiHabitData> = emptyList(),
    val focusTask: UiTaskData? = null
)