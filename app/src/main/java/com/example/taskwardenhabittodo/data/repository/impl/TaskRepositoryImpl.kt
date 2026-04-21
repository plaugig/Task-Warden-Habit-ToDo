package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.data.database.maper.toDomain
import com.example.taskwardenhabittodo.data.database.maper.toEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val local : LocalDataSource
): TaskRepository {
    override fun getAllTasks(): Flow<List<TaskData>> {
        return local.getAllTask().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addTasks(task: TaskData) {
        local.insertTask(task.toEntity())
    }

    override suspend fun deleteTasks(task: TaskData) {
        local.deleteTask(task.toEntity())
    }

    override suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        local.updateTaskStatus(id, isCompleted )
    }
}