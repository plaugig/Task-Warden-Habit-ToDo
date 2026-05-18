package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnfinishHabitsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    fun getUnfinishHabits(startOfDay: Long, endOfDay: Long): Flow<Int> {
        return repository.getUnfinishHabits(startOfDay, endOfDay)
    }
}