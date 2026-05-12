package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksByDayPartUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getTasksByDayPart(
        dayPart: String,
        startOfDay: Long
    ): Flow<List<TaskData>> {
        return repository.getTasksByDayPart(dayPart, startOfDay)
    }
}