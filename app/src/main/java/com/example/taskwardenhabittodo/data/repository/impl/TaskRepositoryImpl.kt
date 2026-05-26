package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import com.example.taskwardenhabittodo.domain.item.toDomain
import com.example.taskwardenhabittodo.domain.item.toEntity
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : TaskRepository {

    override fun getTasksForDay(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<List<TaskData>> {
        return local.getTasksForDay(startOfDay, endOfDay).map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addTask(task: TaskData) {
        withContext(Dispatchers.IO) {
            local.insertTask(task.toEntity())
        }
    }

    override suspend fun deleteTaskById(id: Int) {
        withContext(Dispatchers.IO) {
            local.deleteTaskById(id)
        }
    }

    override suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        withContext(Dispatchers.IO) {
            local.updateTaskStatus(id, isCompleted)
        }
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

    override fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData> {
        return local.getProgressStats(startOfDay)
    }

    override suspend fun getTasksProgressByDay(
        startOfDay: Long,
        endOfDay: Long
    ): ProgressStatsData {
        return withContext(Dispatchers.IO){
            local.getTasksProgressByDay(startOfDay, endOfDay)
        }
    }

    override suspend fun updateTask(task: TaskData) {
        withContext(Dispatchers.IO){
            local.insertTask(task.toEntity())
        }
    }
}