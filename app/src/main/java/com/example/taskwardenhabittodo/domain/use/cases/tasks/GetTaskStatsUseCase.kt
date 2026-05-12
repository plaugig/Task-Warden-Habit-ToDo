package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import com.example.taskwardenhabittodo.domain.item.ProgressStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTaskStatsUseCase @Inject constructor(
    private val repository: TaskRepositoryImpl
) {
    fun getTaskStats(startOfDay: Long): Flow<ProgressStats> {
        return repository.getAllTasks().map { allTasks ->
            val todayTask = allTasks.filter {
              !it.classification && it.createdAt >= startOfDay
            }

            ProgressStats(
                totalCount = todayTask.size,
                completedCount = todayTask.count{ it.isCompleted }
            )
        }
    }
}