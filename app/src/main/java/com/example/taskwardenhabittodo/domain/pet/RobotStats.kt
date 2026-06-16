package com.example.taskwardenhabittodo.domain.pet

import com.example.taskwardenhabittodo.domain.pet.enums.RobotMood

data class RobotStats(
    val disciplineScore: Int = GameConfig.DISCIPLINE_START,
    val lastUpdated: Long = 0L
) {
    fun moodFor(pet: PetWellbeing): RobotMood =
        RobotMood.fromScore(disciplineScore, pet.wellbeing)
}