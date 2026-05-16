package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.item.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserStats(): Flow<UserData?>
    suspend fun updateStats(stats: UserData)

    suspend fun resetDailyPoints()
}