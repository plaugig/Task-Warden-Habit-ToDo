package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "time")
    val time: String?,
    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean = false,
    @ColumnInfo(name = "targetCount")
    val targetCount: Int = 1,
    @ColumnInfo(name = "currentCount")
    val currentCount: Int = 0,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "colorHex")
    val colorHex: Long,
    @ColumnInfo(name = "iconResId")
    val iconResId: Int,
    @ColumnInfo(name = "createdAt")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "lastUpdated")
    val lastUpdated: Long
)