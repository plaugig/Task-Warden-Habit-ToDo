package com.example.taskwardenhabittodo.data.database.dao

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import com.example.taskwardenhabittodo.data.database.entity.HabitEntity
    import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
    import com.example.taskwardenhabittodo.domain.item.data.ProgressStatsData
    import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: HabitEntity)

    @Query("SELECT * FROM habits")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Query("""
        SELECT COUNT(*) FROM habits 
        WHERE isCompleted = 0 
        AND createdAt >= :startOfDay AND createdAt <= :endOfDay
    """)
    fun getUnfinishHabits(startOfDay: Long, endOfDay: Long): Flow<Int>

    @Query("DELETE FROM habits WHERE id = :id")
    suspend fun deleteHabitById(id: Int)

    @Query("""
        UPDATE habits 
        SET currentCount = :count, 
            isCompleted = CASE WHEN :count >= targetCount THEN 1 ELSE 0 END,
            lastUpdated = :timestamp
        WHERE id = :id
    """)
    suspend fun updateHabitProgress(id: Int, count: Int, timestamp: Long)

    @Query("UPDATE habits SET currentCount = 0, isCompleted = 0 WHERE lastUpdated < :startOfDay")
    suspend fun resetOldHabits(startOfDay: Long)

    @Query("""
        SELECT 
            COUNT(*) as totalCount,
            COUNT(CASE WHEN lastUpdated >= :startOfDay 
                       AND lastUpdated <= :endOfDay 
                       AND isCompleted = 1 THEN 1 END) as completedCount
        FROM habits
    """)
    suspend fun getHabitsProgressByDay(startOfDay: Long, endOfDay: Long): ProgressStatsData
}