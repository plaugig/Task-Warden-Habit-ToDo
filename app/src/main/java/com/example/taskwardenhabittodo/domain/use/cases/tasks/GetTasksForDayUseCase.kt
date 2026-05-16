package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksForDayUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskData>>{
        return repository.getTasksForDay(startOfDay, endOfDay)
    }
}