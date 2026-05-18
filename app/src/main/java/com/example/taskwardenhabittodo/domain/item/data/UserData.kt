package com.example.taskwardenhabittodo.domain.item.data

data class UserData(
    val id: Int,
    val petPoints: Int,
    val dailyPoints: Int,
    val fireStreak: Int,
    val masteryStreak: Int,
    val lastStreakCheck: Long
) {
    companion object {
        fun empty() = UserData(
            0,
            0,
            0,
            0,
            0,
            0L
        )
    }
}
