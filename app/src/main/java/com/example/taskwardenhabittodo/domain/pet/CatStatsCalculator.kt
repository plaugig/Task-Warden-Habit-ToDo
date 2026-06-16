package com.example.taskwardenhabittodo.domain.pet

import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import kotlin.math.roundToInt

object CatStatsCalculator {

    private fun Int.clampStat() = coerceIn(GameConfig.STAT_MIN, GameConfig.STAT_MAX)

    fun applyDecay(stats: CatStats, now: Long): CatStats {

        if (stats.lastUpdated <= 0L)
            return stats.copy(lastUpdated = now)

        val elapsedMs = (now - stats.lastUpdated).coerceAtLeast(0L)
        if (elapsedMs == 0L) return stats

        val hours = elapsedMs / 3_600_000f

        val hunger =
            (stats.hunger - GameConfig.DECAY_HUNGER_PER_HOUR * hours).roundToInt().clampStat()
        val thirst =
            (stats.thirst - GameConfig.DECAY_THIRST_PER_HOUR * hours).roundToInt().clampStat()
        val litter =
            (stats.litter - GameConfig.DECAY_LITTER_PER_HOUR * hours).roundToInt().clampStat()
        val stress =
            (stats.stress + GameConfig.STRESS_RISE_PER_HOUR * hours).roundToInt().clampStat()

        val drifted = stats.copy(hunger = hunger, thirst = thirst, litter = litter, stress = stress)
        val target = drifted.wellbeing
        val happiness = (stats.happiness + (target - stats.happiness) * GameConfig.HAPPINESS_DRIFT)
            .roundToInt().clampStat()

        return drifted.copy(happiness = happiness, lastUpdated = now)
    }

    fun applyAction(stats: CatStats, action: CatActionType, new: Long): CatStats {
        return when (action) {
            CatActionType.FEED -> stats.copy(
                hunger = (stats.hunger + GameConfig.EFFECT_FEED_HUNGER).clampStat()
            )

            CatActionType.WATER -> stats.copy(
                thirst = (stats.thirst + GameConfig.EFFECT_WATER_THIRST).clampStat()
            )

            CatActionType.CLEAN -> stats.copy(
                litter = (stats.litter + GameConfig.EFFECT_CLEAN_LITTER).clampStat()
            )

            CatActionType.PET -> stats.copy(
                happiness = (stats.happiness + GameConfig.EFFECT_PET_HAPPINESS).clampStat(),
                stress = (stats.stress + GameConfig.EFFECT_PET_STRESS).clampStat()
            )

            CatActionType.PLAY -> stats.copy(
                happiness = (stats.happiness + GameConfig.EFFECT_PLAY_HAPPINESS).clampStat(),
                stress = (stats.stress + GameConfig.EFFECT_PLAY_STRESS).clampStat(),
                hunger = (stats.hunger + GameConfig.EFFECT_PLAY_HUNGER).clampStat()
            )

            CatActionType.TREAT -> stats.copy(
                happiness = (stats.happiness + GameConfig.EFFECT_TREAT_HAPPINESS).clampStat(),
                hunger = (stats.hunger + GameConfig.EFFECT_TREAT_HUNGER).clampStat()
            )
        }.copy(lastUpdated = new)
    }

    fun applyFailure(stats: CatStats, new: Long, misses: Int): CatStats {
        if (misses <= 0) return stats

        return stats.copy(
            stress = (stats.stress + GameConfig.FAILURE_STRESS_PER_MISS * misses).clampStat(),
            happiness = (stats.happiness - GameConfig.FAILURE_HAPPINESS_PER_MISS * misses).clampStat(),
            lastUpdated = new
        )

    }

    fun applyAutoCore(stats: CatStats, new: Long): CatStats {
        fun rescue(value: Int): Int =
            if (value < GameConfig.AUTOCARE_FLOOR)
                (value + GameConfig.AUTOCARE_TOPUP).clampStat()
            else value

        return stats.copy(
            hunger = rescue(stats.hunger),
            thirst = rescue(stats.thirst),
            litter = rescue(stats.litter),
            lastUpdated = new
        )
    }
}

