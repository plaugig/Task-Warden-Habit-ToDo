package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserStats(): Flow<UserData?>
    suspend fun updateStats(stats: UserData)

    suspend fun resetDailyPoints()

    suspend fun insertDailyProgress(progress: DayProgressData)

    suspend fun updateHabitStreak(habit: Int, timestamp: Long)

    suspend fun updateTaskStreak(task: Int, timestamp: Long)

    fun getAllDaysProgress(): Flow<List<DayProgressData>>

    suspend fun addPoints(delta: Int)

    suspend fun spendPoints(cost: Int)

    suspend fun getPetPoints(): Int

}