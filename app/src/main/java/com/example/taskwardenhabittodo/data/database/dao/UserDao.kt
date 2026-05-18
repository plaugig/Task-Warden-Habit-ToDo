package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE id = 0")
    fun getStats(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStats(stats: UserEntity)

    @Query("UPDATE user SET dailyPoints = 0 WHERE id = 0")
    suspend fun resetDailyPoints()

    @Query("UPDATE user SET fireStreak = :fire, masteryStreak = :mastery, lastStreakCheck = :timestamp WHERE id = 0")
    suspend fun updateStreaks(fire: Int, mastery: Int, timestamp: Long)

    // Теперь tasks и habits — отдельные таблицы, джоиним через подзапросы по дате
    @Query("""
        SELECT 
            t.dateTimestamp,
            t.totalTasks,
            t.completedTasks,
            h.totalHabits,
            h.completedHabits
        FROM (
            SELECT 
                (createdAt / 86400000 * 86400000) as dateTimestamp,
                COUNT(*) as totalTasks,
                COUNT(CASE WHEN isCompleted = 1 THEN 1 END) as completedTasks
            FROM tasks
            GROUP BY dateTimestamp
        ) t
        LEFT JOIN (
            SELECT 
                (createdAt / 86400000 * 86400000) as dateTimestamp,
                COUNT(*) as totalHabits,
                COUNT(CASE WHEN isCompleted = 1 THEN 1 END) as completedHabits
            FROM habits
            GROUP BY dateTimestamp
        ) h ON t.dateTimestamp = h.dateTimestamp
        ORDER BY t.dateTimestamp DESC
    """)
    fun getAllDaysProgress(): Flow<List<DayProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyProgress(progress: DayProgressEntity)
}