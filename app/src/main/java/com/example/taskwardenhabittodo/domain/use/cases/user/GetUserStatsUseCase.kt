package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserStatsUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun getUserStats(): Flow<UserData?> {
        return repository.getUserStats()
    }
}