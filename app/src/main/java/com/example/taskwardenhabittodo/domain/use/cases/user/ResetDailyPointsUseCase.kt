package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.data.repository.impl.UserRepositoryImpl
import javax.inject.Inject

class ResetDailyPointsUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    suspend fun resetDailyPoints() {
        repository.resetDailyPoints()
    }
}