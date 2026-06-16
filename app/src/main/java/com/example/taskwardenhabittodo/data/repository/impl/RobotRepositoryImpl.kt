package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.mapper.toDomain
import com.example.taskwardenhabittodo.domain.item.mapper.toEntity
import com.example.taskwardenhabittodo.domain.pet.RobotStats
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RobotRepositoryImpl @Inject constructor(
    private val local : LocalDataSource
): RobotRepository {
    override fun observeRobot(): Flow<RobotStats?> {
       return local.observeRobot()
            .map { it?.toDomain() }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getRobot(): RobotStats {
        return withContext(Dispatchers.IO){
            local.getRobot()?.toDomain() ?: RobotStats()
        }
    }

    override suspend fun saveRobot(robot: RobotStats) {
      return withContext(Dispatchers.IO){
          local.upsertRobot(robot.toEntity())
      }
    }
}