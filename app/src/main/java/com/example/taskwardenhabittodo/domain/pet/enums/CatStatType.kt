package com.example.taskwardenhabittodo.domain.pet.enums

import com.example.taskwardenhabittodo.domain.pet.GameConfig

enum class CatStatType {
    HUNGER, THIRST, LITTER, STRESS, HAPPINESS
}

enum class CatActionType(val cost: Int) {
    FEED(GameConfig.COST_FEED),
    WATER(GameConfig.COST_WATER),
    CLEAN(GameConfig.COST_CLEAN),
    PET(GameConfig.COST_PET),
    PLAY(GameConfig.COST_PLAY),
    TREAT(GameConfig.COST_TREAT)
}