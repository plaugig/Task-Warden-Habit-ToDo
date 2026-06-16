package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.RobotEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RobotDao {

    @Query("SELECT * FROM robot WHERE id = 0")
    fun observeRobot(): Flow<RobotEntity?>

    @Query("SELECT * FROM robot WHERE id = 0")
    suspend fun getRobot(): RobotEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(robot: RobotEntity)

}