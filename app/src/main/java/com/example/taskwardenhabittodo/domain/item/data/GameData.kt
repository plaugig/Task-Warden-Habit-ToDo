package com.example.taskwardenhabittodo.domain.item.data

import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.RobotStats
import com.example.taskwardenhabittodo.domain.pet.enums.RobotMood

data class GameData(
    val cat: CatStats,
    val robot: RobotStats,
    val petPoints: Int
){
    val robotMood: RobotMood get() = robot.moodFor(cat)
}
