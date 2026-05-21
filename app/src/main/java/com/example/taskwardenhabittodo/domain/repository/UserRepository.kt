package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserStats(): Flow<UserData?>
    suspend fun updateStats(stats: UserData)

    suspend fun resetDailyPoints()

    suspend fun updateStreaks(task: Int, habit: Int, timestamp: Long)

    suspend fun insertDailyProgress(progress: DayProgressData)
}