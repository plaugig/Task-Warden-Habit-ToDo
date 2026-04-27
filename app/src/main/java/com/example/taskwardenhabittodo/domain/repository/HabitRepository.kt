package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.TaskData
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    fun getAllHabits(): Flow<List<TaskData>>

    suspend fun addHabit(habit: TaskData)

    suspend fun deleteHabitById(id: Int)

    suspend fun updateHabitProgress(id: Int, count: Int)

    fun getTodayHabitStats(startOfDay: Long): Flow<Pair<Int, Int>>

    fun getUnfinishHabits(startOfDay: Long, endOfDay: Long): Flow<Int>

}