package com.example.taskwardenhabittodo.domain.item.mapper

import com.example.taskwardenhabittodo.data.database.entity.CatEntity
import com.example.taskwardenhabittodo.data.database.entity.RobotEntity
import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.RobotStats

fun CatEntity.toDomain(): CatStats =
    CatStats(
        hunger = hunger,
        thirst = thirst,
        litter = litter,
        stress = stress,
        happiness = happiness,
        lastUpdated = lastUpdated
    )

fun CatStats.toEntity(): CatEntity =
    CatEntity(
        id = 0,
        hunger = hunger,
        thirst = thirst,
        litter = litter,
        stress = stress,
        happiness = happiness,
        lastUpdated = lastUpdated
    )

fun RobotEntity.toDomain(): RobotStats =
    RobotStats(
        disciplineScore = disciplineScore,
        lastUpdated = lastUpdated
    )

fun RobotStats.toEntity(): RobotEntity =
    RobotEntity(
        id = 0,
        disciplineScore = disciplineScore,
        lastUpdated = lastUpdated
    )