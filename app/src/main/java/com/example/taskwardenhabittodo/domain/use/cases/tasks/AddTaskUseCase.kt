package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun addTask(task: TaskData) {
        repository.addTask(task)
    }
}