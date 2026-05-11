package com.example.taskwardenhabittodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskwardenhabittodo.data.database.dao.HabitDao
import com.example.taskwardenhabittodo.data.database.dao.TaskDao
import com.example.taskwardenhabittodo.data.database.dao.UserDao
import com.example.taskwardenhabittodo.data.database.entity.TaskEntity
import com.example.taskwardenhabittodo.data.database.entity.UserEntity


@Database(
    entities = [TaskEntity::class, UserEntity::class],
    version = 2

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    abstract fun habitDao(): HabitDao

    companion object {
        val MIGRATION_1_2 = object: Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE tasks ADD COLUMN lastUpdated INTEGER NOT NULL DEFAULT 0")
            }
        }
    }

}