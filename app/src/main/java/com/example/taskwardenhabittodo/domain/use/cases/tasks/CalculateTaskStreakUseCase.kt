package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import com.example.taskwardenhabittodo.presentation.habit.item.HabitDateUtils
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CalculateTaskStreakUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() {

        val userStats = userRepository.getUserStats().firstOrNull() ?: return

        val startOfToday = HabitDateUtils.getStartOfDay()

        if (userStats.lastStreakCheck >= startOfToday) return

        val startOfYesterday = startOfToday - 86_400_000L
        val endOfYesterday = startOfToday - 1L

        val stats = taskRepository.getTasksProgressByDay(startOfYesterday, endOfYesterday)

        val newStreak = if (stats.totalCount == 0) {
            0
        } else {

            val completionRatio = stats.completedCount.toFloat() /
                    stats.totalCount.toFloat()

            if (completionRatio >= 0 / 8f) userStats.taskStreak + 1 else 0
        }

        userRepository.updateStreaks(
            task = newStreak,
            habit = userStats.habitStreak,
            timestamp = startOfToday
        )
    }
}