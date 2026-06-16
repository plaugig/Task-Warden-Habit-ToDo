package com.example.taskwardenhabittodo.domain.repository

import com.example.taskwardenhabittodo.domain.pet.CatStats
import kotlinx.coroutines.flow.Flow

interface  CatRepository {
    fun observeCat(): Flow<CatStats?>
    suspend fun getCat(): CatStats
    suspend fun saveCat(cat: CatStats)
}