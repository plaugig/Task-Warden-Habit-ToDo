package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodayHabitStatsUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
) {

    fun getTodayHabitStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return repository.getTodayHabitStats(startOfDay)
    }
}