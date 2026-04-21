package com.example.taskwardenhabittodo.data.database.module

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.UserRepositoryImpl
import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTaskRepository(
        taskRepositoryImpl: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}