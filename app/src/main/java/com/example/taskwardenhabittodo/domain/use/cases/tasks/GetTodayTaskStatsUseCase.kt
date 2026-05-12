package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import com.example.taskwardenhabittodo.domain.item.TaskStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTodayTaskStatsUseCase @Inject constructor(
    private val repository: TaskRepositoryImpl
) {
    fun getTodayTaskStats(startOfDay: Long): Flow<TaskStats> {
        return repository.getAllTasks().map { allTasks ->
            val todayTask = allTasks.filter {
              !it.classification && it.createdAt >= startOfDay
            }

            TaskStats(
                totalCount = todayTask.size,
                completedCount = todayTask.count{ it.isCompleted }
            )
        }
    }
}