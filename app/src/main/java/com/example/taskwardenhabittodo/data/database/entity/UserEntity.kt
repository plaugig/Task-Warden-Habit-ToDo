package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey val id: Int = 0,
    val petPoints: Int = 0,
    @ColumnInfo(name = "dailyPoints")
    val dailyPoints: Int = 0,
    @ColumnInfo(name = "fireStreak")
    val fireStreak: Int = 0,
    @ColumnInfo(name = "masteryStreak")
    val masteryStreak: Int = 0,
    @ColumnInfo(name = "lastStreakCheck")
    val lastStreakCheck: Long = 0L
)