package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.core.application.time.Clock
import com.example.taskwardenhabittodo.domain.pet.CatStatsCalculator
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import javax.inject.Inject

class ApplyCatDecayUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val clock : Clock
) {
    suspend operator fun invoke() {

        val now = clock.now()
        val current = catRepository.getCat()
        val decayed = CatStatsCalculator.applyDecay(current, now)
        val rescued = CatStatsCalculator.applyAutoCore(decayed, now)
        catRepository.saveCat(rescued)

    }
}