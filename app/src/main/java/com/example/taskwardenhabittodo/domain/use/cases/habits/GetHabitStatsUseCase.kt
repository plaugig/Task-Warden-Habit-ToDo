package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHabitStatsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    fun getHabitStats(): Flow<ProgressStatsData> {
        return repository.getAllHabits().map { habits ->
            ProgressStatsData(
                totalCount = habits.size,
                completedCount = habits.count { it.isCompleted }
            )
        }
    }
}