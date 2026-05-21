package com.example.taskwardenhabittodo.data.repository.impl


import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.domain.item.toDomain
import com.example.taskwardenhabittodo.domain.item.toEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local : LocalDataSource
): UserRepository {
    override fun getUserStats(): Flow<UserData?> {
        return local.getStats().map { entity ->
            entity?.toDomain()
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateStats(stats: UserData) {
        local.updateStats(stats.copy(id = 0).toEntity())
    }

    override suspend fun resetDailyPoints() {
        local.resetDailyPoints()
    }

    override suspend fun updateStreaks(
        task: Int,
        habit: Int,
        timestamp: Long
    ) {
        local.updateStreaks(task, habit, timestamp)
    }

    override suspend fun insertDailyProgress(progress: DayProgressData) {
       return local.insertDailyProgress(progress.toEntity())
    }


}