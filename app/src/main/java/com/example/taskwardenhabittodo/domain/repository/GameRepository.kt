package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.RobotStats

interface GameRepository {
    suspend fun saveCatAndRobot(cat: CatStats, robot: RobotStats)
}