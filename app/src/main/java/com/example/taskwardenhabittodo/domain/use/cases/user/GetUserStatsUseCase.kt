package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.data.repository.impl.UserRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserStatsUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    fun getUserStats(): Flow<UserData?> {
        return repository.getUserStats()
    }
}