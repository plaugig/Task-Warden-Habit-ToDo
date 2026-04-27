package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.domain.use.cases.tasks.UpdateCompletionUseCase
import com.example.taskwardenhabittodo.domain.use.cases.user.GetUserStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.user.ResetDailyPointsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val getUserStatsUseCase: GetUserStatsUseCase,
    private val resetDailyPointsUseCase: ResetDailyPointsUseCase,
    private val updateCompletionUseCase: UpdateCompletionUseCase
) {
    fun getUserStats(): Flow<UserData?> {
        return getUserStatsUseCase.getUserStats()
    }

    suspend fun resetDailyPoints() {
        resetDailyPointsUseCase.resetDailyPoints()
    }

    suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        updateCompletionUseCase.updateCompletion(id, isCompleted)
    }

}
