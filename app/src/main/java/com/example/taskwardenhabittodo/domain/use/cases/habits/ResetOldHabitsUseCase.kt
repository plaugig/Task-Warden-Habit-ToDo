package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import javax.inject.Inject

class ResetOldHabitsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend fun resetOldHabits(startOfDay: Long) {
        repository.resetOldHabits(startOfDay)
    }
}