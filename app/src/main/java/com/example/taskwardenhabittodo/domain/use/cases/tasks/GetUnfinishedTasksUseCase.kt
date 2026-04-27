package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnfinishedTasksUseCase @Inject constructor(
    private val repository: TaskRepositoryImpl
) {
    fun getUnfinishedTasks(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return repository.getUnfinishedTasks(startOfDay, endOfDay)
    }
}