package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.taskwardenhabittodo.data.database.entity.CatEntity
import com.example.taskwardenhabittodo.data.database.entity.RobotEntity


@Dao
abstract class GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsertCat(cat: CatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun upsertRobot(robot: RobotEntity)

    @Transaction
    open suspend fun saveCatAndRobot(cat: CatEntity, robot: RobotEntity) {
        upsertCat(cat)
        upsertRobot(robot)
    }
}