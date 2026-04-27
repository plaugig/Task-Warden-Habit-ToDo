package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: TaskRepositoryImpl
) {
    fun getAllTasks(): Flow<List<TaskData>> {
        return repository.getAllTasks()
    }
}