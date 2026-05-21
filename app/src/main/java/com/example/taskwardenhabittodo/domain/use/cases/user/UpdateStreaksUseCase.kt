package com.example.taskwardenhabittodo.domain.use.cases.user

import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class UpdateStreaksUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun updateStreaks(task: Int, habit: Int, timestamp: Long) {
        userRepository.updateStreaks(task, habit, timestamp)
    }
}