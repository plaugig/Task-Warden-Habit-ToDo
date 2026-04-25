package com.example.taskwardenhabittodo.ui

data class UiUserData (
    val id: Int,
    val petPoints: Int,
    val dailyPoints: Int,
    val fireStreak: Int,
    val masteredCount: Int,
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val targetMastery: Int = 4
)