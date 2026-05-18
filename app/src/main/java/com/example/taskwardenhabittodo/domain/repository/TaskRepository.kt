package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.domain.item.data.DayProgressData
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import com.example.taskwardenhabittodo.domain.item.data.TaskData
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskData>>

    suspend fun addTask(task: TaskData)

    suspend fun deleteTaskById(id: Int)

    suspend fun updateCompletion(id: Int, isCompleted: Boolean)

    fun getUnfinishedTasks(startOfDay: Long, endOfDay: Long): Flow<Int>

    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskData>>

    fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData>


}