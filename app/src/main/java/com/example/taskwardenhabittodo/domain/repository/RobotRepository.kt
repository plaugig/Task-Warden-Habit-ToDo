package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.pet.RobotStats
import kotlinx.coroutines.flow.Flow

interface RobotRepository {
    fun observeRobot(): Flow<RobotStats?>
    suspend fun getRobot(): RobotStats
    suspend fun saveRobot(robot: RobotStats)
}