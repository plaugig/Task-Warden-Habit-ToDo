package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserStats(): Flow<UserData?>

    suspend fun saveStats(stats: UserData)
}