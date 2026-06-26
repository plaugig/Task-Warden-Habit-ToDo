package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import com.example.taskwardenhabittodo.R

data class CatUiState (
    @DrawableRes val backgroundRes: Int = R.drawable.room4,
    @DrawableRes val catImageRes: Int = R.drawable.the_cat_is_afraid,
    val stats: List<CatUiData> = emptyList(),
    val isInteractionBlocked: Boolean = false
)
