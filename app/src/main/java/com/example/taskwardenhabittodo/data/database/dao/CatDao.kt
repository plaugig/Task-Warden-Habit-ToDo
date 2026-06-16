package com.example.taskwardenhabittodo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskwardenhabittodo.data.database.entity.CatEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CatDao {
    @Query("SELECT * FROM cat WHERE id = 0")
    fun observeCat(): Flow<CatEntity?>
    @Query("SELECT * FROM cat WHERE id = 0")
    suspend fun getCat(): CatEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cat: CatEntity)
}