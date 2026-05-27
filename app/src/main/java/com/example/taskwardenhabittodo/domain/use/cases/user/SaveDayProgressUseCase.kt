package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import com.example.taskwardenhabittodo.presentation.habit.item.HabitDateUtils
import javax.inject.Inject

class SaveDayProgressUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val habitRepository: HabitRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        val startOfToday = HabitDateUtils.getStartOfDay()
        val startOfYesterday = startOfToday - 86_400_000L
        val endOfYesterday = startOfToday - 1L

        val taskStats = taskRepository.getTasksProgressByDay(startOfYesterday, endOfYesterday)
        val habitStats = habitRepository.getHabitsProgressByDay(startOfYesterday, endOfYesterday)

        if (taskStats.totalCount == 0 && habitStats.totalCount == 0) return

        val progress = DayProgressData(
            dateTimestamp = startOfYesterday,
            totalTasks = taskStats.totalCount,
            completedTasks = taskStats.completedCount,
            totalHabits = habitStats.totalCount,
            completedHabits = habitStats.completedCount
        )

        userRepository.insertDailyProgress(progress)
    }
}