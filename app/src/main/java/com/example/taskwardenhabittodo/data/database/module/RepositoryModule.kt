package com.example.taskwardenhabittodo.data.database.module

import com.example.taskwardenhabittodo.data.repository.impl.CatRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.GameRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.HabitRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.RobotRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import com.example.taskwardenhabittodo.data.repository.impl.UserRepositoryImpl
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import com.example.taskwardenhabittodo.domain.repository.GameRepository
import com.example.taskwardenhabittodo.domain.repository.HabitRepository
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
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

    @Binds
    @Singleton
    abstract fun bindHabitRepository(
        impl: HabitRepositoryImpl
    ): HabitRepository

    @Binds
    @Singleton
    abstract fun bindCatRepository(impl: CatRepositoryImpl): CatRepository

    @Binds
    @Singleton
    abstract fun bindRobotRepository(impl: RobotRepositoryImpl): RobotRepository

    @Binds
    @Singleton
    abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository
}