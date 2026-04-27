package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.data.maper.toDomain
import com.example.taskwardenhabittodo.data.maper.toEntity
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : HabitRepository {
    override fun getAllHabits(): Flow<List<TaskData>> {
        return local.getAllHabits()
            .map { list -> list.map { it.toDomain() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun addHabit(habit: TaskData) = withContext(Dispatchers.IO) {
        local.insertHabit(habit.toEntity())
    }

    override suspend fun deleteHabitById(id: Int) {
        local.deleteHabitById(id)
    }

    override suspend fun updateHabitProgress(id: Int, count: Int) {
        local.updateHabitProgress(id, count)
    }

    override fun getTodayHabitStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return combine(
            local.getTotalHabitsCount(startOfDay),
            local.getCompletedHabits(startOfDay)
        ) { total, completed ->
            total to completed
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getUnfinishHabits(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return local.getUnfinishedHabits(startOfDay, endOfDay)
    }

}