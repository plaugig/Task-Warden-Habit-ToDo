package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.data.repository.impl.UserRepositoryImpl
import javax.inject.Inject

class UpdateStatsUseCase @Inject constructor(
    private val repository : UserRepositoryImpl
){
    suspend fun updateStats(stats: UserData) {
        repository.updateStats(stats)
    }
}