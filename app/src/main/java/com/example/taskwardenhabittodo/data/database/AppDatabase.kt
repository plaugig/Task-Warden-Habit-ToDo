package com.example.taskwardenhabittodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE tasks ADD COLUMN lastUpdated INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `day_progress` (
                        `dateTimestamp` INTEGER NOT NULL, 
                        `totalTasks` INTEGER NOT NULL, 
                        `completedTasks` INTEGER NOT NULL, 
                        `totalHabits` INTEGER NOT NULL, 
                        `completedHabits` INTEGER NOT NULL, 
                        PRIMARY KEY(`dateTimestamp`)
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Создаём новую таблицу habits
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `habits` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `title` TEXT NOT NULL,
                        `description` TEXT NOT NULL,
                        `time` TEXT,
                        `isCompleted` INTEGER NOT NULL DEFAULT 0,
                        `targetCount` INTEGER NOT NULL DEFAULT 1,
                        `currentCount` INTEGER NOT NULL DEFAULT 0,
                        `category` TEXT NOT NULL,
                        `colorHex` INTEGER NOT NULL,
                        `iconResId` INTEGER NOT NULL,
                        `createdAt` INTEGER NOT NULL,
                        `lastUpdated` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    INSERT INTO habits (id, title, description, time, isCompleted, targetCount, currentCount, category, colorHex, iconResId, createdAt, lastUpdated)
                    SELECT id, title, description, time, isCompleted, targetCount, currentCount, category, colorHex, iconResId, createdAt, lastUpdated
                    FROM tasks
                    WHERE classification = 1
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `tasks_new` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `title` TEXT NOT NULL,
                        `description` TEXT NOT NULL,
                        `priority` TEXT NOT NULL,
                        `time` TEXT,
                        `isCompleted` INTEGER NOT NULL DEFAULT 0,
                        `isPinned` INTEGER NOT NULL DEFAULT 0,
                        `dayPart` TEXT NOT NULL,
                        `period` TEXT NOT NULL,
                        `createdAt` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    INSERT INTO tasks_new (id, title, description, priority, time, isCompleted, isPinned, dayPart, period, createdAt)
                    SELECT id, title, description, priority, time, isCompleted, isPinned, dayPart, period, createdAt
                    FROM tasks
                    WHERE classification = 0
                    """.trimIndent()
                )

                db.execSQL("DROP TABLE tasks")
                db.execSQL("ALTER TABLE tasks_new RENAME TO tasks")
            }
        }
    }
}