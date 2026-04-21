package com.example.taskwardenhabittodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskwardenhabittodo.data.database.dao.TaskDao
import com.example.taskwardenhabittodo.data.database.dao.UserDao
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity


@Database(entities = [TaskEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

}