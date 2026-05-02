package com.example.taskwardenhabittodo.ui.components.habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.theme.spacing


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HabitsFlowGrid(
    habits: List<UiTaskData>,
    onIncrement: (Int, Int) -> Unit,
    onDelete: (Int) -> Unit
){
    val spacing = MaterialTheme.spacing

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.medium),
        verticalArrangement = Arrangement.spacedBy(spacing.medium),
    ) {
        habits.forEach { habit ->
            HabitCard(
                habit = habit,
                modifier = Modifier.weight(1f),
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