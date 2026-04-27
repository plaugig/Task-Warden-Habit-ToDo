package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.data.maper.toDomain
import com.example.taskwardenhabittodo.data.maper.toEntity
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : TaskRepository {
    override fun getAllTasks(): Flow<List<TaskData>> {
        return local.getAllTasks().map { entities ->
            entities.map { it.toDomain() }
        }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun addTask(task: TaskData) {
        local.insertTask(task.toEntity())
    }

    override suspend fun deleteTaskById(id: Int) {
        local.deleteTaskById(id)
    }

    override suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        local.updateTaskStatus(id, isCompleted)
    }

    override fun getTodayTaskStats(startOfDay: Long): Flow<Pair<Int, Int>> {
        return combine(
            local.getTotalTodayTasks(startOfDay),
            local.getCompletedTasks(startOfDay)
        ) { total, completed ->
            total to completed
        }
            .flowOn(Dispatchers.IO)
    }

    override fun getUnfinishedTasks(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return local.getUnfinishedTasksByDate(startOfDay, endOfDay)
    }

    override fun getTasksByDayPart(
        dayPart: String,
        startOfDay: Long
    ): Flow<List<TaskData>> {
        return local.getTasksByDayPart(dayPart, startOfDay).map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.IO)
    }
}