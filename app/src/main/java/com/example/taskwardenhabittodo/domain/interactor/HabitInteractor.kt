package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.use.cases.habits.AddHabitUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.DeleteHabitByIdUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetAllHabitsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetTodayHabitStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetUnfinishHabitsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.UpdateHabitProgressUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitInteractor @Inject constructor(
    private val addHabitUseCase: AddHabitUseCase,
    private val deleteHabitByIdUseCase: DeleteHabitByIdUseCase,
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val getTodayHabitStatsUseCase: GetTodayHabitStatsUseCase,
    private val getUnfinishHabitsUseCase: GetUnfinishHabitsUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase
){
    suspend fun addHabit(habit: TaskData) {
        addHabitUseCase.addHabit(habit)
    }

    suspend fun deleteHabitById(id: Int) {
        deleteHabitByIdUseCase.deleteHabitById(id)
    }

    fun getAllHabits(): Flow<List<TaskData>> {
        return getAllHabitsUseCase.getAllHabits()
    }

    fun getTodayHabitStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return getTodayHabitStatsUseCase.getTodayHabitStats(startOfDay)
    }

    fun getUnfinishHabits(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return getUnfinishHabitsUseCase.getUnfinishHabits(startOfDay, endOfDay)
    }

    suspend fun updateHabitProgress(id: Int, count: Int) {
        updateHabitProgressUseCase.updateHabitProgress(id, count)
    }
}