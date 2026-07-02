package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType

data class CatUiData(
    val title: String,
    val statusLabel: String,
    val progress: Float,
    @DrawableRes val iconRes: Int,
    val accentColor: Color,
    val iconBgColor: Color,
    val actions: List<CatStatAction> = emptyList()

)

data class CatStatAction(
    val action: CatActionType,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int
)
