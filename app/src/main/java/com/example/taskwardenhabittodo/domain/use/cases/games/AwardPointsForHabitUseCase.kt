package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.domain.pet.GameConfig
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import javax.inject.Inject

class AwardPointsForHabitUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val robotRepository: RobotRepository
) {
    suspend operator fun invoke(justCompleted: Boolean){
        val gain = GameConfig.POINTS_PER_HABIT_TICK +
                if (justCompleted) GameConfig.POINTS_HABIT_COMPLETE_BONUS else 0
        userRepository.addPoints(gain)

        val robot = robotRepository.getRobot()
        val newDiscipline = (robot.disciplineScore + GameConfig.DISCIPLINE_GAIN_PER_HABIT)
            .coerceIn(GameConfig.STAT_MIN, GameConfig.STAT_MAX)
        robotRepository.saveRobot(robot.copy(disciplineScore = newDiscipline))
    }

}