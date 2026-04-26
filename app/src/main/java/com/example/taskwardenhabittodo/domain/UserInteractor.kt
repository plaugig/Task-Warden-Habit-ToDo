package com.example.taskwardenhabittodo.domain

import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.domain.use.cases.GetUserStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.SaveStatsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val getUserStatsUseCase: GetUserStatsUseCase,
    private val saveStatsUseCase: SaveStatsUseCase
) {
    fun getUserStats(): Flow<UserData?> {
        return getUserStatsUseCase.getUserStats()
    }

    suspend fun saveStats(stats: UserData){
        saveStatsUseCase.saveStats(stats)
    }
}