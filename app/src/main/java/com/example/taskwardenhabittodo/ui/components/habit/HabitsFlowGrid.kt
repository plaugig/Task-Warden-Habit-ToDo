package com.example.taskwardenhabittodo.ui.components.habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskwardenhabittodo.presentation.item.UiHabitData
import com.example.taskwardenhabittodo.ui.theme.spacing


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HabitsFlowGrid(
    habits: List<UiHabitData>,
    onIncrement: (Int, Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    val spacing = MaterialTheme.spacing

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.medium),
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
        maxItemsInEachRow = 2
    ) {
        habits.forEach { habit ->
            HabitCard(
                habit = habit,
                modifier = Modifier.fillMaxWidth(0.47f),
                onIncrement = {
                    onIncrement(habit.id, habit.currentCount)
                },
                onLongClick = {
                    onDelete(habit.id)
                }
            )
        }
    }
}