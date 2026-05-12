package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.TaskData
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskData>>

    suspend fun addTask(task: TaskData)

    suspend fun deleteTaskById(id: Int)

    suspend fun updateCompletion(id: Int, isCompleted: Boolean)

    fun getUnfinishedTasks(startOfDay: Long, endOfDay: Long): Flow<Int>

    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskData>>
}