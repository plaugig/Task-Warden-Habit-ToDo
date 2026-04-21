package com.example.taskwardenhabittodo.data.database.entity

import androidx.compose.foundation.MutatePriority
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "priority")
    val priority: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "isCompleted")
    val isCompleted : Boolean = false,
    @ColumnInfo(name = "isHabit")
    val isHabit: Boolean = false,
    @ColumnInfo(name = "targetCount")
    val targetCount: Int = 1,
    @ColumnInfo(name = "currentCount")
    val currentCount: Int = 0,
    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis()
)