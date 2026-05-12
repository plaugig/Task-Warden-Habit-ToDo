package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun deleteTaskById(id: Int) {
        repository.deleteTaskById(id)
    }
}