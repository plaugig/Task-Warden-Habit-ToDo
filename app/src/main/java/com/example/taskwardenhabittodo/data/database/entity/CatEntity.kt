package com.example.taskwardenhabittodo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cat")
data class CatEntity (
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "hunger") val hunger: Int = 70,
    @ColumnInfo(name = "thirst") val thirst: Int = 70,
    @ColumnInfo(name = "litter") val litter: Int = 80,
    @ColumnInfo(name = "stress") val stress: Int = 20,
    @ColumnInfo(name = "happiness") val happiness: Int = 70,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: Long = 0L
)