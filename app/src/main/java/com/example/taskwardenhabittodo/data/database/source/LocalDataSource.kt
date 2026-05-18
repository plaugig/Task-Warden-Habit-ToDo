package com.example.taskwardenhabittodo.data.database.source

import com.example.taskwardenhabittodo.data.database.AppDatabase
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.HabitEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {
    // хабиты

    suspend fun insertHabit(habit: HabitEntity) {
        appDatabase.habitDao().insertHabit(habit)
    }

    fun getAllHabits(): Flow<List<HabitEntity>> {
        return appDatabase.habitDao().getAllHabits()
    }

    fun getUnfinishedHabits(startOfDay: Long, endOfDay: Long): Flow<Int> {
        return appDatabase.habitDao().getUnfinishHabits(startOfDay, endOfDay)
    }

    suspend fun deleteHabitById(id: Int) {
        appDatabase.habitDao().deleteHabitById(id)
    }

    suspend fun updateHabitProgress(id: Int, count: Int, timestamp: Long) {
        appDatabase.habitDao().updateHabitProgress(id, count, timestamp)
    }

    suspend fun resetOldHabits(startOfDay: Long) {
        appDatabase.habitDao().resetOldHabits(startOfDay)
    }

    // таски

    suspend fun insertTask(task: TaskEntity) {
        appDatabase.taskDao().insertTask(task)
    }

    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>> {
        return appDatabase.taskDao().getTasksForDay(startOfDay, endOfDay)
    }

    suspend fun deleteTaskById(id: Int) {
        appDatabase.taskDao().deleteTaskById(id)
    }

    suspend fun updateTaskStatus(id: Int, completed: Boolean) {
        appDatabase.taskDao().updateTaskStatus(id, completed)
    }

    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>> {
        return appDatabase.taskDao().getTasksByDayPart(dayPart, startOfDay)
    }

    fun getUnfinishedTasksByDate(startOfDay: Long, endOfDay: Long): Flow<Int> {
        return appDatabase.taskDao().getUnfinishedTasks(startOfDay, endOfDay)
    }

    fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData> {
        return appDatabase.taskDao().getProgressStats(startOfDay)
    }

    // юзер

    fun getStats(): Flow<UserEntity?> {
        return appDatabase.userDao().getStats()
    }

    suspend fun updateStats(stats: UserEntity) {
        appDatabase.userDao().updateStats(stats)
    }

    suspend fun resetDailyPoints() {
        appDatabase.userDao().resetDailyPoints()
    }

    suspend fun updateStreaks(fire: Int, mastery: Int, timestamp: Long) {
        appDatabase.userDao().updateStreaks(fire, mastery, timestamp)
    }

    fun getAllDaysProgress(): Flow<List<DayProgressEntity>> {
        return appDatabase.userDao().getAllDaysProgress()
    }

    suspend fun insertDailyProgress(progress: DayProgressEntity) {
        appDatabase.userDao().insertDailyProgress(progress)
    }
}