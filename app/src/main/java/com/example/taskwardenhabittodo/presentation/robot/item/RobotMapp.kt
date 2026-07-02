package com.example.taskwardenhabittodo.presentation.robot.item

import androidx.annotation.DrawableRes
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.pet.enums.RobotMood

@DrawableRes
fun RobotMood.toRobotImageRes(): Int = when (this) {
    RobotMood.RADIANT   -> R.drawable.robot_radiant
    RobotMood.CHEERFUL  -> R.drawable.robot_cheerful
    RobotMood.NEUTRAL   -> R.drawable.robot_neutral
    RobotMood.ANNOYED   -> R.drawable.robot_annoyed
    RobotMood.SARCASTIC -> R.drawable.robot_sarcastic
    RobotMood.MENACING  -> R.drawable.robot_menacing
}