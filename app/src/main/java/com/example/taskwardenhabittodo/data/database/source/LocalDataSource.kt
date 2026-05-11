package com.example.taskwardenhabittodo.data.database.source

import com.example.taskwardenhabittodo.data.database.AppDatabase
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {
    suspend fun updateHabitProgress(id: Int, count: Int, timestamp: Long){
        appDatabase.habitDao().updateHabitProgress(id,count, timestamp)
    }

    fun getAllHabits(): Flow<List<TaskEntity>>{
      return  appDatabase.habitDao().getAllHabits()
    }

    fun getTotalHabitsCount(): Flow<Int>{
      return  appDatabase.habitDao().getTotalHabitsCount()
    }

    fun getCompletedHabits(startOfDay: Long): Flow<Int>{
        return  appDatabase.habitDao().getCompletedHabits(startOfDay)
    }

    fun getUnfinishedHabits(startOfDay: Long, endOfDay: Long): Flow<Int>{
        return appDatabase.habitDao().getUnfinishHabits(startOfDay,endOfDay)
    }

    suspend fun insertHabit(habit: TaskEntity){
        appDatabase.habitDao().insertHabit(habit)
    }

    suspend fun deleteHabitById(id: Int){
        appDatabase.habitDao().deleteHabitById(id)
    }

    suspend fun resetOldHabits (startOfDay: Long){
        appDatabase.habitDao().resetOldHabits(startOfDay)
    }

    //

    fun getAllTasks(): Flow<List<TaskEntity>>{
        return appDatabase.taskDao().getAllTasks()
    }

    suspend fun insertTask(task: TaskEntity){
        appDatabase.taskDao().insertTask(task)
    }

    suspend fun deleteTaskById(id: Int){
        appDatabase.taskDao().deleteTaskById(id)
    }

    suspend fun updateTaskStatus(id: Int, completed: Boolean){
        appDatabase.taskDao().updateTaskStatus(id, completed)
    }

    fun getTotalTodayTasks(startOfDay: Long): Flow<Int>{
        return appDatabase.taskDao().getTotalTodayTasks(startOfDay)
    }

    fun getCompletedTasks(startOfDay: Long): Flow<Int>{
        return appDatabase.taskDao().getCompletedTasks(startOfDay)
    }

    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>>{
        return appDatabase.taskDao().getTasksByDayPart(dayPart,startOfDay)
    }

    //

    fun getStats(): Flow<UserEntity?>{
        return appDatabase.userDao().getStats()
    }

    suspend fun updateStats(stats: UserEntity){
        appDatabase.userDao().updateStats(stats)
    }

    suspend fun resetDailyPoints(){
        appDatabase.userDao().resetDailyPoints()
    }

    fun getUnfinishedTasksByDate(startOfDay: Long, endOfDay: Long): Flow<Int> {
        return appDatabase.taskDao().getUnfinishedTasks(startOfDay,endOfDay)
    }


}