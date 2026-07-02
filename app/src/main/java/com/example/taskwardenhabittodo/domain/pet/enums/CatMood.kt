package com.example.taskwardenhabittodo.domain.pet.enums

import com.example.taskwardenhabittodo.domain.pet.CatStats

enum class CatMood {
    ANGRY, AFRAID, NEUTRAL, CHILLING;

    companion object {
        fun fromStats(stats: CatStats): CatMood = when {
            stats.happiness < 25 -> ANGRY
            stats.happiness < 45 ->AFRAID
            stats.happiness < 70 -> NEUTRAL
            else ->  CHILLING
        }
    }

}