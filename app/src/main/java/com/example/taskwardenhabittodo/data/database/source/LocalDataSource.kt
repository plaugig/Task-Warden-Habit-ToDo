package com.example.taskwardenhabittodo.data.database.source

import com.example.taskwardenhabittodo.data.database.AppDatabase
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
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

    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>{
        return appDatabase.taskDao().getTasksForDay(startOfDay, endOfDay)
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

    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>>{
        return appDatabase.taskDao().getTasksByDayPart(dayPart,startOfDay)
    }

    fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData>{
        return appDatabase.taskDao().getProgressStats(startOfDay)
    }

    fun getAllDaysProgress () :Flow<List<DayProgressEntity>>{
        return appDatabase.taskDao().getAllDaysProgress()
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