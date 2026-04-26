package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.data.maper.toDomain
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val local : LocalDataSource
): HabitRepository {
    override fun getAllHabits(): Flow<List<TaskData>> {
        return local.getAllHabits().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addHabit(habit: TaskData) {

    }

    override suspend fun deleteHabitById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateHabitProgress(id: Int, count: Int) {
        local.updateHabitProgress(id, count)
    }

    override fun getTodayHabitStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return combine(
            local.getTotalHabitsCount(startOfDay),
            local.getCompletedHabitsCount(startOfDay)
        ) { total, completed -> total to completed }
    }

    override fun getUnfinishHabits(
        startOfYesterday: Long,
        endOfYesterday: Long
    ): Flow<Int> {
        TODO("Not yet implemented")
    }

}