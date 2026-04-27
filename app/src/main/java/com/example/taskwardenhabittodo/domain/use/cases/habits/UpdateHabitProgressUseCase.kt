package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import javax.inject.Inject

class UpdateHabitProgressUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
) {
    suspend fun updateHabitProgress(id: Int, count: Int) {
        repository.updateHabitProgress(id, count)
    }
}