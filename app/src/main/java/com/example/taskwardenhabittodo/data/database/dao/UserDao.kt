package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
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

    @Query("UPDATE user SET taskStreak = :task, habitStreak = :habit, lastStreakCheck = :timestamp WHERE id = 0")
    suspend fun updateStreaks(task: Int, habit: Int, timestamp: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyProgress(progress: DayProgressEntity)
}