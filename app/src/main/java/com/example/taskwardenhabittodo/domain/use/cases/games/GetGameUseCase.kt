package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.domain.item.data.GameData
import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.RobotStats
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import com.example.taskwardenhabittodo.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetGameUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val robotRepository: RobotRepository,
    private val userRepository: UserRepository
) {

    operator fun invoke(): Flow<GameData> {
        return combine(
            catRepository.observeCat(),
            robotRepository.observeRobot(),
            userRepository.getUserStats()
        ) { cat, robot, user ->
            GameData(
                cat = cat ?: CatStats(),
                robot = robot ?: RobotStats(),
                petPoints = user?.petPoints ?: 0
            )
        }
    }

}