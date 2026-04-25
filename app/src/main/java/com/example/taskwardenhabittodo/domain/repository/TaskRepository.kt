package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.TaskData
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTasks(): Flow<List<TaskData>>

    suspend fun addTasks(task: TaskData)

    suspend fun deleteTasks(task: TaskData)

    suspend fun updateCompletion(id: Int, isCompleted: Boolean)

    suspend fun updateHabitProgress(id: Int, count: Int)

    suspend fun resetDailyPoints()
}