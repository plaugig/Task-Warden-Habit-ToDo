package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.item.ProgressStats
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHabitStatsUseCase @Inject constructor(
    private val repository: HabitRepository
) {

    fun getHabitStats(): Flow<ProgressStats> {
        return repository.getAllHabits().map { allHabit ->
            val activeHabits = allHabit.filter { it.classification }

            ProgressStats(
                totalCount = activeHabits.size,
                completedCount = activeHabits.count{it.isCompleted}
            )
        }
    }
}