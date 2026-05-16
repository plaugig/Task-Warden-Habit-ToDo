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
    @Query("""
    SELECT * FROM tasks 
    WHERE classification = 0 
      AND createdAt >= :startOfDay 
      AND createdAt <= :endOfDay 
    ORDER BY isPinned DESC, time ASC
""")
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun deleteTaskById(id: Int)

    @Query("UPDATE tasks SET isCompleted = :completed WHERE id = :id")
    suspend fun updateTaskStatus(id: Int, completed: Boolean)
    

    @Query("SELECT * FROM tasks WHERE dayPart = :dayPart AND createdAt >= :startOfDay ORDER BY time ASC")
    fun getTasksByDayPart(dayPart: String, startOfDay: Long): Flow<List<TaskEntity>>

    @Query("""
    SELECT COUNT(*) FROM tasks 
    WHERE classification = 0 AND isCompleted = 0 
    AND createdAt >= :startOfDay AND createdAt <= :endOfDay
""")
    fun getUnfinishedTasks(startOfDay: Long, endOfDay: Long): Flow<Int>

    @Query("""
    SELECT 
        COUNT(*) as totalCount, 
        COUNT(CASE WHEN isCompleted = 1 THEN 1 END) as completedCount 
    FROM tasks 
    WHERE classification = 0 AND createdAt >= :startOfDay
""")
    fun getProgressStats(startOfDay: Long): Flow<ProgressStatsData>

    @Query("""
    SELECT 
        (createdAt / 86400000 * 86400000) as dateTimestamp,
        COUNT(*) as totalCount,
        COUNT(CASE WHEN isCompleted = 1 THEN 1 END) as completedCount
    FROM tasks
    WHERE classification = 0 -- Фильтруем только обычные задачи (не привычки)
    GROUP BY dateTimestamp
    ORDER BY dateTimestamp DESC -- Свежие дни будут вверху списка
""")
    fun getAllDaysProgress(): Flow<List<DayProgressEntity>>


}