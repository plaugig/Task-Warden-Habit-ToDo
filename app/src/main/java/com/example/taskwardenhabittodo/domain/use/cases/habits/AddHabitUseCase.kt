package com.example.taskwardenhabittodo.domain.use.cases.habits

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import javax.inject.Inject

class AddHabitUseCase @Inject constructor(
    private val repository: HabitRepositoryImpl
) {
    suspend fun addHabit(habit: TaskData) {
        repository.addHabit(habit)
    }
}