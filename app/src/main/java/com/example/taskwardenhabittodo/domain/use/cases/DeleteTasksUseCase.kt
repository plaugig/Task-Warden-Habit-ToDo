package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteTasksUseCase @Inject constructor(
    private val repository: TaskRepository
){
    suspend fun deleteTasks(task: TaskData){
        repository.deleteTasks(task)
    }
}