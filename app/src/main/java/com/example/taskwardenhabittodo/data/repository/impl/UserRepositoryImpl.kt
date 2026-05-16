package com.example.taskwardenhabittodo.data.repository.impl


import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.domain.item.toDomain
import com.example.taskwardenhabittodo.domain.item.toEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
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
            entity?.toDomain() ?: UserData(
                id = 0,
                dailyPoints = 0,
                masteryStreak = 0,
                fireStreak = 0,
                petPoints = 0
            )
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateStats(stats: UserData) {
        local.updateStats(stats.copy(id = 0).toEntity())
    }

    override suspend fun resetDailyPoints() {
        local.resetDailyPoints()
    }


}