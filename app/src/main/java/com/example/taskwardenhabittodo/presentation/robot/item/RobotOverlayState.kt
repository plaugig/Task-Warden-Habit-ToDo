package com.example.taskwardenhabittodo.presentation.robot.item

import androidx.annotation.DrawableRes
import com.example.taskwardenhabittodo.R

data class RobotOverlayState(
    val visible: Boolean = false,
    val fullText: String = "",
    @DrawableRes val robotRes: Int = R.drawable.robot_neutral
)