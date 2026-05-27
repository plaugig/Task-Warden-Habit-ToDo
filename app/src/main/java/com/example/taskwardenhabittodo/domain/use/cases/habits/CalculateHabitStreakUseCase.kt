package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import com.example.taskwardenhabittodo.presentation.habit.item.HabitDateUtils
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class CalculateHabitStreakUseCase @Inject constructor(
    private val habitRepository: HabitRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() {
        val userStats = userRepository.getUserStats().firstOrNull() ?: return

        val startOfToday  = HabitDateUtils.getStartOfDay()

        if (userStats.lastHabitStreakCheck >= startOfToday ) return

        val startOfYesterday = startOfToday  - 86_400_000L
        val endOfYesterday = startOfToday - 1L

        val stats = habitRepository.getHabitsProgressByDay(startOfYesterday, endOfYesterday)

        if (stats.totalCount == 0) return

        val newHabitStreak = if (stats.completedCount == stats.totalCount){
            userStats.habitStreak + 1
        } else {
            0
        }

        userRepository.updateHabitStreak(
            habit = newHabitStreak,
            timestamp = startOfToday
        )
    }
}