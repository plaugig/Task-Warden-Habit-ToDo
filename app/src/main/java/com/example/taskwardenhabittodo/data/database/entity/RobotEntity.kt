package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "robot")
data class RobotEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "disciplineScore") val disciplineScore: Int = 70,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: Long = 0L
)
