package com.example.taskwardenhabittodo.data.database.entity

import androidx.compose.foundation.MutatePriority
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "priority")
    val priority: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean = false,
    @ColumnInfo(name = "isHabit")
    val isHabit: Boolean = false,
    @ColumnInfo(name = "targetCount")
    val targetCount: Int = 1,
    @ColumnInfo(name = "currentCount")
    val currentCount: Int = 0,
    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "colorHex")
    val colorHex: Long = 0xFF34EAB9,
    @ColumnInfo(name = "isPinned")
    val isPinned: Boolean = false,
    @ColumnInfo(name = "dayPart")
    val dayPart: String,
    @ColumnInfo(name = "period")
    val period: String,
    @ColumnInfo(name = "iconResId")
    val iconResId: Int
)