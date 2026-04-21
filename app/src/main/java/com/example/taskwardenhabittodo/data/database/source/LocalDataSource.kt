package com.example.taskwardenhabittodo.data.database.source

import com.example.taskwardenhabittodo.data.database.AppDatabase
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {
    fun getStats(): Flow<UserEntity?> {
      return  appDatabase.userDao().getStats()
    }

    suspend fun updateStats(stats: UserEntity){
        appDatabase.userDao().updateStats(stats)
    }

    fun getAllTask(): Flow<List<TaskEntity>>{
       return appDatabase.taskDao().getAllTasks()
    }

    suspend fun insertTask(task: TaskEntity){
        appDatabase.taskDao().insertTask(task)
    }

    suspend fun deleteTask(task: TaskEntity){
        appDatabase.taskDao().deleteTask(task)
    }

    suspend fun updateTaskStatus(id: Int, completed: Boolean){
        appDatabase.taskDao().updateTaskStatus(id,completed)
    }
}