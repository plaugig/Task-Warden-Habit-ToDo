package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.core.application.time.Clock
import com.example.taskwardenhabittodo.domain.pet.CatStatsCalculator
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class PerformCatActionUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val userRepository: UserRepository,
    private val clock: Clock
) {
    suspend operator fun invoke(action: CatActionType): CatActionResult {
        val available = userRepository.getPetPoints()
        if (available < action.cost) {
            return CatActionResult.InsufficientPoints(needed = action.cost, available = available)
        }

        val now = clock.now()
        val decayed = CatStatsCalculator.applyDecay(catRepository.getCat(), now)
        val update = CatStatsCalculator.applyAction(decayed, action, now)

        userRepository.spendPoints(action.cost)
        catRepository.saveCat(update)

        return CatActionResult.Success(
            updated = update,
            remainingPoints = (available - action.cost).coerceAtLeast(0)
        )
    }
}