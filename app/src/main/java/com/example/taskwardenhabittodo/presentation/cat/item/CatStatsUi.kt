package com.example.taskwardenhabittodo.presentation.cat.item

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.pet.CatStats
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import com.example.taskwardenhabittodo.ui.theme.extendedColors

@Composable
fun CatStats.toStatBars(): List<CatUiData> {
    val colors = MaterialTheme.extendedColors

    return listOf(

        statBar(
            title = stringResource(R.string.cat_stat_mood),
            value = happiness,
            iconRes = R.drawable.mood_heart,
            accent = colors.statMood,
            actions = listOf(
                CatStatAction(CatActionType.PET,   R.string.cat_action_pet,   R.drawable.hand),
                CatStatAction(CatActionType.PLAY,  R.string.cat_action_play,  R.drawable.animal),
                CatStatAction(CatActionType.TREAT, R.string.cat_action_treat, R.drawable.bolt)
            )
        ),

        statBar(
            title = stringResource(R.string.cat_stat_fullness),
            value = hunger,
            iconRes = R.drawable.food,
            accent = colors.statFull,
            actions = listOf(
                CatStatAction(CatActionType.FEED, R.string.cat_action_feed, R.drawable.food)
            )
        ),

        statBar(
            title = stringResource(R.string.cat_stat_thirst),
            value = thirst,
            iconRes = R.drawable.drink,
            accent = colors.statThirst,
            actions = listOf(
                CatStatAction(CatActionType.WATER, R.string.cat_action_water, R.drawable.drink)
            )
        ),

        statBar(
            title = stringResource(R.string.cat_stat_litter),
            value = litter,
            iconRes = R.drawable.bowl,
            accent = colors.statLitter,
            actions = listOf(
                CatStatAction(CatActionType.CLEAN, R.string.cat_action_clean, R.drawable.autorenew)
            )
        ),

        // Calm — только инфо, кнопок нет (actions = emptyList по умолчанию)
        statBar(
            title = stringResource(R.string.cat_stat_calm),
            value = 100 - stress,
            iconRes = R.drawable.meditate,
            accent = colors.statCalm
        )
    )
}

@Composable
private fun statBar(
    title: String,
    value: Int,
    @DrawableRes iconRes: Int ,
    accent: Color,
    actions: List<CatStatAction> = emptyList()
): CatUiData = CatUiData(
    title = title,
    statusLabel = stringResource(levelLabel(value)),
    progress = value / 100f,
    iconRes = iconRes,
    accentColor = accent,
    iconBgColor = accent.copy(alpha = 0.15f),
    actions = actions
)

@StringRes
private fun levelLabel(value: Int): Int = when {
    value >= 75 -> R.string.cat_level_great
    value >= 50 -> R.string.cat_level_good
    value >= 25 -> R.string.cat_level_low
    else        -> R.string.cat_level_critical
}
