package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.domain.item.data.HabitData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import com.example.taskwardenhabittodo.domain.use.cases.habits.AddHabitUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.DeleteHabitByIdUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetAllHabitsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetHabitStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.GetUnfinishHabitsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.ResetOldHabitsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.habits.UpdateHabitProgressUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HabitInteractor @Inject constructor(
    private val addHabitUseCase: AddHabitUseCase,
    private val deleteHabitByIdUseCase: DeleteHabitByIdUseCase,
    private val getAllHabitsUseCase: GetAllHabitsUseCase,
    private val getHabitStatsUseCase: GetHabitStatsUseCase,
    private val getUnfinishHabitsUseCase: GetUnfinishHabitsUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase,
    private val resetOldHabitsUseCase: ResetOldHabitsUseCase
){
    suspend fun addHabit(habit: HabitData) {
        addHabitUseCase.addHabit(habit)
    }

    suspend fun deleteHabitById(id: Int) {
        deleteHabitByIdUseCase.deleteHabitById(id)
    }

    fun getAllHabits(): Flow<List<HabitData>> {
        return getAllHabitsUseCase.getAllHabits()
    }

    fun getTodayHabitStats(): Flow<ProgressStatsData> {
        return getHabitStatsUseCase.getHabitStats()
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

    suspend fun resetOldHabits(startOfDay: Long){
        resetOldHabitsUseCase.resetOldHabits(startOfDay)
    }
}