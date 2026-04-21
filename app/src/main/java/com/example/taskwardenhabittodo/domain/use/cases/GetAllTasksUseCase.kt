package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val tasksRepository: TaskRepository
) {

    fun getAllTasks(): Flow<List<TaskData>> {
        return tasksRepository.getAllTasks()
    }
}