package com.example.taskwardenhabittodo.presentation.cat.item

import com.example.taskwardenhabittodo.domain.item.data.GameData

fun GameData.toCatUiState(): CatUiState  = CatUiState(
    cat = cat,
    petPoints = petPoints
)