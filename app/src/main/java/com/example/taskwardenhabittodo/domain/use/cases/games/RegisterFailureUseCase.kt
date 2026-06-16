package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.core.application.time.Clock
import com.example.taskwardenhabittodo.domain.pet.CatStatsCalculator
import com.example.taskwardenhabittodo.domain.pet.GameConfig
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import com.example.taskwardenhabittodo.domain.repository.GameRepository
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import javax.inject.Inject

class RegisterFailureUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val robotRepository: RobotRepository,
    private val gameRepository: GameRepository,
    private val clock: Clock
) {
    suspend operator fun invoke (misses: Int){
        if (misses <= 0) return

        val now = clock.now()

        val decayedCat = CatStatsCalculator.applyDecay(catRepository.getCat(), now)
        val stressedCat = CatStatsCalculator.applyFailure(decayedCat, now, misses)

        val newRobot = robotRepository.getRobot().copy(
            disciplineScore = (
                    robotRepository.getRobot().disciplineScore -
                            GameConfig.FAILURE_DISCIPLINE_PER_MISS * misses
                    ).coerceIn(GameConfig.STAT_MIN , GameConfig.STAT_MAX),
            lastUpdated = now
        )

        gameRepository.saveCatAndRobot(stressedCat, newRobot)
    }
}