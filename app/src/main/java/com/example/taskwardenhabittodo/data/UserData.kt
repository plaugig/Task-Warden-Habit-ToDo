package com.example.taskwardenhabittodo.data

import androidx.room.ColumnInfo

data class UserData(
    val id: Int,
    val petHunger: Float = 1.0f,
    val petMood: Float = 1.0f
)
