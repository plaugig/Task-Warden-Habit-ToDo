package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitByIdUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend fun deleteHabitById(id: Int) {
        repository.deleteHabitById(id)
    }
}