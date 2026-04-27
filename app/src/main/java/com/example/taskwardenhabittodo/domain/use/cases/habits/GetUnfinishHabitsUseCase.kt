package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnfinishHabitsUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
){
    fun getUnfinishHabits(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return repository.getUnfinishHabits(startOfDay, endOfDay)
    }
}