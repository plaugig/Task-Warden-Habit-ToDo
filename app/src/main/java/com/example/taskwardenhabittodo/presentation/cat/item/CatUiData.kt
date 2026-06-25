package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class CatUiData(
    val title: String,
    val statusLabel: String,
    val progress: Float,
    @DrawableRes val iconRes: Int,
    val accentColor: Color,
    val iconBgColor: Color
)
