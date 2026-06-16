package com.example.taskwardenhabittodo.data.repository.impl

import com.example.taskwardenhabittodo.data.database.source.LocalDataSource
import com.example.taskwardenhabittodo.domain.item.mapper.toDomain
import com.example.taskwardenhabittodo.domain.item.mapper.toEntity
import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatRepositoryImpl @Inject constructor(
    private val local: LocalDataSource
) : CatRepository {
    override fun observeCat(): Flow<CatStats?> {
        return local.observeCat()
            .map { it?.toDomain() }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getCat(): CatStats {
        return withContext(Dispatchers.IO) {
            local.getCat()?.toDomain() ?: CatStats()
        }
    }

    override suspend fun saveCat(cat: CatStats) {
        return withContext(Dispatchers.IO) {
            local.upsertCat(cat.toEntity())
        }
    }
}