package com.example.taskwardenhabittodo.presentation.item

data class UiUserData(
    val petPoints: Int,
    val dailyPoints: Int,
    val fireStreak: Int,
    val masteryStreak: Int
)

data class DayProgress(
    val totalTasks: Int,
    val completedTasks: Int,
    val totalHabits: Int,
    val completedHabits: Int
) {

    val tasksProgress: Float
        get() = if (totalTasks > 0) completedTasks.toFloat() /
                totalTasks else 0f
    val habitsProgress: Float
        get() = if (totalHabits > 0) {
            completedHabits.toFloat() / totalHabits.toFloat()
        } else 0f
}