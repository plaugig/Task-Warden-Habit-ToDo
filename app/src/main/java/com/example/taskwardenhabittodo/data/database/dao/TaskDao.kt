package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("""
        SELECT * FROM tasks 
        WHERE createdAt >= :startOfDay 
          AND createdAt <= :endOfDay 
        ORDER BY isPinned DESC, time ASC
    """)
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: Int)

    @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :id")
    suspend fun updateTaskStatus(id: Int, completed: Boolean)

    @Query("SELECT * FROM tasks WHERE dayPart = :dayPart AND createdAt >= :startOfDay ORDER BY time ASC")
    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>>

    @Query("""
        SELECT COUNT(*) FROM tasks 
        WHERE isCompleted = 0 
        AND createdAt >= :startOfDay AND createdAt <= :endOfDay
    """)
    fun getUnfinishedTasks(startOfDay: Long, endOfDay: Long): Flow<Int>

    @Query("""
        SELECT 
            COUNT(*) as totalCount, 
            COUNT(CASE WHEN isCompleted = 1 THEN 1 END) as completedCount 
        FROM tasks 
        WHERE createdAt >= :startOfDay
    """)
    fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData>
}