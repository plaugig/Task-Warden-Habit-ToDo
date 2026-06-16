package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.domain.pet.GameConfig
import com.example.taskwardenhabittodo.domain.repository.GameRepository
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class AwardPointsForTaskUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val robotRepository: RobotRepository
) {
    suspend operator fun invoke(isCompleted: Boolean){
        val sign = if (isCompleted) 1 else -1

        userRepository.addPoints(GameConfig.POINTS_PER_TASK * sign)

        val newDiscipline = (
                robotRepository.getRobot().disciplineScore + GameConfig.DISCIPLINE_GAIN_PER_TASK * sign
                ).coerceIn(GameConfig.STAT_MIN, GameConfig.STAT_MAX)
        robotRepository.saveRobot(robotRepository.getRobot().copy(disciplineScore = newDiscipline))
    }
}