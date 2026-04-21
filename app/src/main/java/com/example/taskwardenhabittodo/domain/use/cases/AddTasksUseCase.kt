package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class AddTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend fun addTasks(task: TaskData){
        taskRepository.addTasks(task)
    }
}