package com.example.taskwardenhabittodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskwardenhabittodo.data.database.dao.HabitDao
import com.example.taskwardenhabittodo.data.database.dao.TaskDao
import com.example.taskwardenhabittodo.data.database.dao.UserDao
import com.example.taskwardenhabittodo.data.database.entity.DayProgressEntity
import com.example.taskwardenhabittodo.data.database.entity.HabitEntity
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity

@Database(
    entities = [TaskEntity::class, HabitEntity::class, UserEntity::class, DayProgressEntity::class],
    version = 4
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao


}