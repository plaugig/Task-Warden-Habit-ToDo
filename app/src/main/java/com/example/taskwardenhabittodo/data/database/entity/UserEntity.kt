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
    @ColumnInfo(name = "taskStreak")
    val taskStreak: Int = 0,
    @ColumnInfo(name = "habitStreak")
    val habitStreak: Int = 0,
    @ColumnInfo(name = "lastTaskStreakCheck")
    val lastTaskStreakCheck: Long = 0L,
    @ColumnInfo(name = "lastHabitStreakCheck")
    val lastHabitStreakCheck: Long = 0L
)