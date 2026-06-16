package com.example.taskwardenhabittodo.domain.pet.enums

import com.example.taskwardenhabittodo.domain.pet.GameConfig

enum class RobotMood {
    MENACING, SARCASTIC, ANNOYED, NEUTRAL, CHEERFUL, RADIANT;

    val isHostile: Boolean get() = this == MENACING || this == SARCASTIC

    companion object {

        fun fromScore(disciplineScore: Int, petWellbeing: Int): RobotMood {
            val blended = disciplineScore * GameConfig.MOOD_DISCIPLINE_WEIGHT +
                    petWellbeing * GameConfig.MOOD_WELLBEING_WEIGHT
            return when {
                blended >= 80f -> RADIANT
                blended >= 62f -> CHEERFUL
                blended >= 46f -> NEUTRAL
                blended >= 30f -> ANNOYED
                blended >= 15f -> SARCASTIC
                else -> MENACING
            }
        }
    }
}
enum class RobotContext {
    GREETING, TASK_DONE, HABIT_DONE, STREAK_UP, FAILURE,
    PET_FED, PET_PETTED, LOW_POINTS, IDLE_NUDGE
}
