package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitProgressUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend fun updateHabitProgress(id: Int, count: Int) {
        repository.updateHabitProgress(id, count)
    }
}