package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class ResetDailyPointsUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun resetDailyPoints() {
        repository.resetDailyPoints()
    }
}