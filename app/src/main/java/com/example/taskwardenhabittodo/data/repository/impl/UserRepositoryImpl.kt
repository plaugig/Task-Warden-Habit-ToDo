package com.example.taskwardenhabittodo.data.repository.impl


import com.example.taskwardenhabittodo.data.UserData
import com.example.taskwardenhabittodo.data.database.maper.toDomain
import com.example.taskwardenhabittodo.data.database.maper.toEntity
import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val local : LocalDataSource
): UserRepository {
    override fun getUserStats(): Flow<UserData?> {
        return local.getStats().map { it?.toDomain() }
    }

    override suspend fun saveStats(user: UserData) {
       local.updateStats(user.toEntity())
    }
}