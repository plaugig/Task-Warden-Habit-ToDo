package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.domain.pet.CatStats

sealed interface CatActionResult {
    data class Success(val updated: CatStats, val remainingPoints: Int) : CatActionResult
    data class InsufficientPoints(val needed: Int, val available: Int) : CatActionResult
}