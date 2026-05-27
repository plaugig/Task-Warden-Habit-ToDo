package com.example.taskwardenhabittodo.data.database.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.taskwardenhabittodo.data.database.AppDatabase
import com.example.taskwardenhabittodo.data.database.dao.HabitDao
import com.example.taskwardenhabittodo.data.database.dao.TaskDao
import com.example.taskwardenhabittodo.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "task_warden_db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL(
                        """
                        INSERT INTO user 
                        (id, petPoints, dailyPoints, taskStreak, habitStreak, 
                         lastTaskStreakCheck, lastHabitStreakCheck) 
                        VALUES (0, 0, 0, 0, 0, 0, 0)
                        """.trimIndent()
                    )
                }
            })
            .build()
    }

    @Provides
    fun provideTaskDao(db : AppDatabase): TaskDao = db.taskDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun  provideHabitDao(db: AppDatabase): HabitDao = db.habitDao()

}