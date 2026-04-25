package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateHabitProgress @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun updateHabitProgress(id: Int, count: Int) {
        repository.updateHabitProgress(id, count)
    }
}