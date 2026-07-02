package com.example.taskwardenhabittodo.domain.pet

object GameConfig {

    const val STAT_MIN = 0
    const val STAT_MAX = 100

    // начисление поинтов
    const val POINTS_PER_TASK = 10
    const val POINTS_PER_HABIT_TICK = 5
    const val POINTS_HABIT_COMPLETE_BONUS = 10

    // цена кота
    const val COST_FEED = 12
    const val COST_WATER = 8
    const val COST_CLEAN = 15
    const val COST_PET = 5
    const val COST_PLAY = 10
    const val COST_TREAT = 20

    //эфект действий
    const val EFFECT_FEED_HUNGER = 40
    const val EFFECT_WATER_THIRST = 40
    const val EFFECT_CLEAN_LITTER = 55
    const val EFFECT_PET_HAPPINESS = 15
    const val EFFECT_PET_STRESS = -10
    const val EFFECT_PLAY_HAPPINESS = 25
    const val EFFECT_PLAY_STRESS = -15
    const val EFFECT_PLAY_HUNGER = -5
    const val EFFECT_TREAT_HAPPINESS = 30
    const val EFFECT_TREAT_HUNGER = 15

    // деградация
    const val DECAY_HUNGER_PER_HOUR = 8
    const val DECAY_THIRST_PER_HOUR = 10
    const val DECAY_LITTER_PER_HOUR = 6
    const val STRESS_RISE_PER_HOUR = 5

    /** Доля, на которую счастье за тик подтягивается к wellbeing (0..1). */
    const val HAPPINESS_DRIFT = 0.30f

    // провал
    const val FAILURE_STRESS_PER_MISS = 8
    const val FAILURE_HAPPINESS_PER_MISS = 5
    const val FAILURE_DISCIPLINE_PER_MISS = 6

    // робот дисциплина
    const val DISCIPLINE_START = 70
    const val DISCIPLINE_GAIN_PER_TASK = 3
    const val DISCIPLINE_GAIN_PER_HABIT = 2

    const val LOW_POINTS_THRESHOLD = 10

    //форумула настроения робота
    const val MOOD_DISCIPLINE_WEIGHT = 0.6f
    const val MOOD_WELLBEING_WEIGHT = 0.4f

    // автозабота
    const val AUTOCARE_FLOOR = 20
    const val AUTOCARE_TOPUP = 15
}