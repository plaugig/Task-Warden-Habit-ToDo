package com.example.taskwardenhabittodo.data

import androidx.room.ColumnInfo

data class UserData(
    val id: Int,
    val petPoints: Int,
    val dailyPoints: Int,
    val fireStreak: Int,
    val masteryStreak: Int
)
