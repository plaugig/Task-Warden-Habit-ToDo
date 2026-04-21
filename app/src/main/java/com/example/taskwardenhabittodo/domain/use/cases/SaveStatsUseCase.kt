package com.example.taskwardenhabittodo.domain.use.cases


import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class SaveStatsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun saveStats(stats: UserData){
        userRepository.saveStats(stats)
    }
}