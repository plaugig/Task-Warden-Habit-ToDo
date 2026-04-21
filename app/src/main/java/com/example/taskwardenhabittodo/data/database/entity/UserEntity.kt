package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey val
    id: Int = 0,
    @ColumnInfo(name = "petHunger")
    val petHunger: Float = 1.0f,
    @ColumnInfo(name = "petMood")
    val petMood: Float = 1.0f
)