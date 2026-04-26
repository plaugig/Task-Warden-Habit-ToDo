package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE isHabit = 0 ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: Int)

    @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :id")
    suspend fun updateTaskStatus(id: Int, completed: Boolean)

    @Query("SELECT COUNT(*) FROM tasks WHERE isHabit = 0 AND createdAt >= :startOfDay")
    fun getTotalTodayTasks(startOfDay: Long): Flow<Int>

    @Query("SELECT COUNT(*) FROM tasks WHERE isHabit = 0 AND isCompleted = 1 AND createdAt >= :startOfDay")
    fun getCompletedTasks(startOfDay: Long): Flow<Int>

    @Query("SELECT * FROM tasks WHERE dayPart = :dayPart AND createdAt >= :startOfDay ORDER BY time ASC")
    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>>


}