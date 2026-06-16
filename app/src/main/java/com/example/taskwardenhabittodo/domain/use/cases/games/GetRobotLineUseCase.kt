package com.example.taskwardenhabittodo.domain.use.cases.games

import com.example.taskwardenhabittodo.domain.pet.RobotDialogue
import com.example.taskwardenhabittodo.domain.pet.enums.RobotContext
import com.example.taskwardenhabittodo.domain.repository.CatRepository
import com.example.taskwardenhabittodo.domain.repository.RobotRepository
import javax.inject.Inject

class GetRobotLineUseCase @Inject constructor(
    private val catRepository: CatRepository,
    private val robotRepository: RobotRepository
) {
    suspend operator fun invoke (
        context: RobotContext,
        allowProfanity : Boolean =  false
    ): String {
        val cat = catRepository.getCat()
        val robot = robotRepository.getRobot()
        val mood = robot.moodFor(cat)
        return RobotDialogue.line(mood, context, allowProfanity)
    }
}