package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun updateTask(task: TaskData) {
        repository.updateTask(task)
    }
}