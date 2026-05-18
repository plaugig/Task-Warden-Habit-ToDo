package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.HabitData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import kotlin.collections.all

class ResetDailyStreaksUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val habitRepository: HabitRepository,
    private val userRepository: UserRepository
) {
    suspend fun resetDailyStreaks(startOfToday: Long) {

        val userStats = userRepository.getUserStats().firstOrNull() ?: return
        if (userStats.lastStreakCheck >= startOfToday) return

        val startOfYesterday = startOfToday - 86400000L
        val endOfYesterday = startOfToday - 1L

        val yesterdayTasks = taskRepository.getTasksForDay(startOfYesterday, endOfYesterday)
            .firstOrNull() ?: emptyList()

        val allHabits = habitRepository.getAllHabits().firstOrNull() ?: emptyList()

        val yesterdayProgress = DayProgressData(
            dateTimestamp = startOfYesterday,
            totalTasks = yesterdayTasks.size,
            completedTasks = yesterdayTasks.count { it.isCompleted },
            totalHabits = allHabits.size,
            completedHabits = allHabits.count { it.currentCount >= it.targetCount }
        )

        userRepository.insertDailyProgress(yesterdayProgress)

        val newFireStreak = calculateFireStreak(userStats.fireStreak, yesterdayTasks)
        val newMasteryStreak = calculateMasteryStreak(userStats.masteryStreak, allHabits)

        userRepository.updateStreaks(
            fire = newFireStreak,
            mastery = newMasteryStreak,
            timestamp = startOfToday
        )

        habitRepository.resetOldHabits(startOfToday)
    }

    private fun calculateFireStreak(currentStreak: Int, yesterdayTasks: List<TaskData>): Int {
        if (yesterdayTasks.isEmpty()) return currentStreak

        val completedTasks = yesterdayTasks.count { it.isCompleted }
        val totalTasks = yesterdayTasks.size
        val taskSuccessRatio = completedTasks.toFloat() / totalTasks

        return if (taskSuccessRatio >= 0.8f) currentStreak + 1 else 0
    }

    private fun calculateMasteryStreak(currentStreak: Int, habits: List<HabitData>): Int {
        if (habits.isEmpty()) return currentStreak

        val allHabitsDone = habits.all { it.currentCount >= it.targetCount }
        return if (allHabitsDone) currentStreak + 1 else 0
    }
}