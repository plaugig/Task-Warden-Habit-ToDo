package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserStats(): Flow<UserData?>
    suspend fun updateStats(stats: UserData)

    suspend fun resetDailyPoints()

    suspend fun updateStreaks(fire: Int, mastery: Int, timestamp: Long)

    fun getAllDaysProgress():Flow<List<DayProgressData>>

    suspend fun insertDailyProgress(progress: DayProgressData)
}