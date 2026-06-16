package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.mapper.toEntity
import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.RobotStats
import com.example.taskwardenhabittodo.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
): GameRepository {
    override suspend fun saveCatAndRobot(
        cat: CatStats,
        robot: RobotStats
    ) {
        withContext(Dispatchers.IO){
            local.saveCatAndRobot(cat.toEntity(), robot.toEntity())
        }
    }
}