package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.pet.enums.CatMood


@DrawableRes
fun CatMood.toCatImageRes(): Int = when (this) {
    CatMood.ANGRY    -> R.drawable.cat_angry
    CatMood.AFRAID   -> R.drawable.cat_afraid
    CatMood.NEUTRAL  -> R.drawable.cat_neutral_1
    CatMood.CHILLING -> R.drawable.cat_neutral_2
}