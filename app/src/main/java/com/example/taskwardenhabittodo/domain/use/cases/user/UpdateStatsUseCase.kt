package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class UpdateStatsUseCase @Inject constructor(
    private val repository : UserRepository
){
    suspend fun updateStats(stats: UserData) {
        repository.updateStats(stats)
    }
}