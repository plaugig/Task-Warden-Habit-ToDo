package com.example.taskwardenhabittodo.data.repository.impl


import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.UserData
import com.example.taskwardenhabittodo.domain.item.toDomain
import com.example.taskwardenhabittodo.domain.item.toEntity
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : UserRepository {
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

    override suspend fun insertDailyProgress(progress: DayProgressData) {
        return local.insertDailyProgress(progress.toEntity())
    }

    override suspend fun updateHabitStreak(habit: Int, timestamp: Long) {
        withContext(Dispatchers.IO){
            local.updateHabitStreak(habit, timestamp)
        }
    }

    override suspend fun updateTaskStreak(task: Int, timestamp: Long) {
        withContext(Dispatchers.IO){
            local.updateTaskStreak(task, timestamp)
        }
    }

    override fun getAllDaysProgress(): Flow<List<DayProgressData>> {
        return local.getAllDaysProgress()
            .map { list ->
                list.map { it.toDomain() }
            }
            .flowOn(Dispatchers.IO)
    }


}