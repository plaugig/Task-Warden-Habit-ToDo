package com.example.taskwardenhabittodo.data.database.module

import android.content.Context
import androidx.room.Room
import com.example.taskwardenhabittodo.data.database.AppDatabase
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
        ).build()
    }

    @Provides
    fun provideTaskDao(db : AppDatabase): TaskDao = db.taskDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

}