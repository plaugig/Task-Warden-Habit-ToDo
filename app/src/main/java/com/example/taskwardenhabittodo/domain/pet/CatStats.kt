package com.example.taskwardenhabittodo.domain.pet

data class CatStats(
    val hunger: Int = 70,
    val thirst: Int = 70,
    val litter: Int = 80,
    val stress: Int = 20,
    val happiness: Int = 70,
    val lastUpdated: Long = System.currentTimeMillis()
) : PetWellbeing {
    override val wellbeing: Int
        get() {
            val calmness = GameConfig.STAT_MAX - stress
            return ((hunger + thirst + litter + calmness) / 4)
                .coerceIn(GameConfig.STAT_MIN, GameConfig.STAT_MAX)
        }

    val isThriving: Boolean
        get() = hunger >= 50 && thirst >= 50 && litter >= 50 &&
                stress <= 50 && happiness >= 50
}
