package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayTaskStatsUseCase @Inject constructor(
    private val repository: TaskRepositoryImpl
) {
    fun getTodayTaskStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return repository.getTodayTaskStats(startOfDay)
    }
}