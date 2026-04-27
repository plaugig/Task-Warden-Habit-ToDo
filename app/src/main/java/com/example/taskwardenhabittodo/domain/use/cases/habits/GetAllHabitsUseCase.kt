package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHabitsUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
) {
    fun getAllHabits(): Flow<List<TaskData>> {
        return repository.getAllHabits()
    }
}