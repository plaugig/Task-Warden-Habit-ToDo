package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskStatsUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getTaskStats(startOfDay: Long): Flow<ProgressStatsData> =
        repository.getProgressStats(startOfDay)
}