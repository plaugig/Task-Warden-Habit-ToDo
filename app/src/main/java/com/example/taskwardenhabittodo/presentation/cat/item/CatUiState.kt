package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.pet.CatStats

data class CatUiState(
    val cat: CatStats? = null,
    val petPoints: Int = 0,
    @DrawableRes val backgroundRes: Int = R.drawable.room4
)
