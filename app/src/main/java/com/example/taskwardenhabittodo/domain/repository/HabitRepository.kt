package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.item.data.HabitData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    fun getAllHabits(): Flow<List<HabitData>>

    suspend fun addHabit(habit: HabitData)

    suspend fun deleteHabitById(id: Int)

    suspend fun updateHabitProgress(id: Int, count: Int)

    fun getUnfinishHabits(startOfDay: Long, endOfDay: Long): Flow<Int>

    suspend fun resetOldHabits(startOfDay: Long)
}