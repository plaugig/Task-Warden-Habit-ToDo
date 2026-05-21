package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.domain.use.cases.user.GetUserStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.user.ResetDailyPointsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.user.UpdateStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.user.UpdateStreaksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val getUserStatsUseCase: GetUserStatsUseCase,
    private val resetDailyPointsUseCase: ResetDailyPointsUseCase,
    private val updateStatsUseCase: UpdateStatsUseCase,
    private val updateStreaksUseCase: UpdateStreaksUseCase,
) {
    fun getUserStats(): Flow<UserData?> {
        return getUserStatsUseCase.getUserStats()
    }

    suspend fun resetDailyPoints() {
        resetDailyPointsUseCase.resetDailyPoints()
    }

    suspend fun updateStreaks(task: Int, habit: Int, timestamp: Long) {
        updateStreaksUseCase.updateStreaks(task, habit, timestamp)
    }
}
