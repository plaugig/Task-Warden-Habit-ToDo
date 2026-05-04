package com.example.taskwardenhabittodo.data.database.dao

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface HabitDao {

        @Query("""
        UPDATE tasks 
        SET currentCount = :count, 
            isCompleted = CASE WHEN :count >= targetCount THEN 1 ELSE 0 END 
        WHERE id = :id
    """)
        suspend fun updateHabitProgress(id: Int, count: Int)

        @Query("SELECT * FROM tasks WHERE isHabit = 1")
        fun getAllHabits(): Flow<List<TaskEntity>>

        @Query("SELECT COUNT(*) FROM tasks WHERE isHabit = 1 AND createdAt >= :startOfDay")
        fun getTotalHabitsCount(startOfDay: Long): Flow<Int>

        @Query(
            "SELECT COUNT(*) FROM tasks WHERE isHabit = 1 AND isCompleted = 1 AND createdAt >= :startOfDay"
        )
        fun getCompletedHabits(startOfDay: Long): Flow<Int>

        @Query("""
            SELECT COUNT(*) FROM tasks 
            WHERE isHabit = 1 AND isCompleted = 0 
            AND createdAt >= :startOfDay AND createdAt <= :endOfDay
        """)
        fun getUnfinishHabits(startOfDay: Long, endOfDay: Long): Flow<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertHabit(habit: TaskEntity)

        @Query("DELETE FROM tasks WHERE id = :id AND isHabit = 1")
        suspend fun deleteHabitById(id: Int)

    }