package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import javax.inject.Inject

class DeleteHabitByIdUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
) {
    suspend fun deleteHabitById(id: Int) {
        repository.deleteHabitById(id)
    }
}