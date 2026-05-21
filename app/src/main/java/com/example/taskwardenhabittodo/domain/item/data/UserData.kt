package com.example.taskwardenhabittodo.domain.item.data

data class UserData(
    val id: Int,
    val petPoints: Int,
    val dailyPoints: Int,
    val taskStreak: Int,
    val habitStreak: Int,
    val lastTaskStreakCheck: Long,
    val lastHabitStreakCheck: Long
) {

}
