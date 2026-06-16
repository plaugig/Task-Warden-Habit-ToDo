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

    @Query("""
        UPDATE user 
        SET taskStreak = :task, 
            lastTaskStreakCheck = :timestamp 
        WHERE id = 0
    """)
    suspend fun updateTaskStreak(task: Int, timestamp: Long)

    @Query("""
        UPDATE user 
        SET habitStreak = :habit, 
            lastHabitStreakCheck = :timestamp 
        WHERE id = 0
    """)
    suspend fun updateHabitStreak(habit: Int, timestamp: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyProgress(progress: DayProgressEntity)

    @Query("SELECT * FROM day_progress ORDER BY dateTimestamp DESC")
    fun getAllDaysProgress(): Flow<List<DayProgressEntity>>


    @Query(
        """
        UPDATE user 
        SET petPoints = MAX(0, petPoints + :delta), 
            dailyPoints = MAX(0, dailyPoints + :delta) 
        WHERE id = 0
        """
    )
    suspend fun addPoints(delta: Int)

    @Query("UPDATE user SET petPoints = MAX(0, petPoints - :cost) WHERE id = 0")
    suspend fun spendPoints(cost: Int)

    @Query("SELECT COALESCE(petPoints, 0) FROM user WHERE id = 0")
    suspend fun getPetPoints(): Int
}