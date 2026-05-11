package com.example.taskwardenhabittodo.presentation.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.ActionType
import com.example.taskwardenhabittodo.domain.DayPart
import com.example.taskwardenhabittodo.domain.Priority
import com.example.taskwardenhabittodo.presentation.task.item.TaskDateUtils
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.components.ActionIconButton
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.components.bottom.sheet.BottomSheetScreen
import com.example.taskwardenhabittodo.ui.components.task.TaskCard
import com.example.taskwardenhabittodo.ui.components.task.TaskSectionHeader
import com.example.taskwardenhabittodo.ui.theme.AmberGold
import com.example.taskwardenhabittodo.ui.theme.SuccessGreen
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing


@Composable
fun TasksScreen(
    viewModel: TaskScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme
    val extendedColors = MaterialTheme.extendedColors

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium, vertical = spacing.small)
            ) {
                Text(
                    text = uiState.displayDate,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.tasks_title),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    ActionIconButton(
                        iconRes = R.drawable.add,
                        onClick = { viewModel.showBottomSheet() },
                        isSelected = true,
                        actionColor = colorScheme.primary,
                        containerSize = 48.dp
                    )
                }
            }
        },
        containerColor = colorScheme.background
    ) { paddingValues ->

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(spacing.medium)
            ) {
                item {
                    FocusTaskCard(
                        title = stringResource(R.string.daily_progress),
                        completedCount = uiState.progress.completedCount,
                        totalCount = uiState.progress.totalCount,
                        progress = uiState.progress.percentage,
                        isFullMode = false,
                        streak = uiState.fireStreak
                    )
                    Spacer(modifier = Modifier.height(spacing.medium))
                }

                // oтрисовка секций
                uiState.sections.forEach { section ->
                    item {
                        TaskSectionHeader(
                            title = section.title,
                            iconRes = section.iconRes,
                            doneCount = section.tasks.count { it.isCompleted },
                            totalCount = section.tasks.size
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    items(
                        items = section.tasks,
                        key = { it.id }
                    ) { task ->
                        TaskCard(
                            time = task.time,
                            period = task.period,
                            title = task.title,
                            duration = 15,
                            category = task.category.name,
                            priorityColor = when (task.priority){
                                Priority.HIGH -> WarningRed
                                Priority.MEDIUM -> AmberGold
                                Priority.LOW -> SuccessGreen
                                Priority.NONE -> null
                            },
                            isCompleted = task.isCompleted,
                            onClick = { viewModel.toggleTaskCompletion(task) }
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    item {
                        Spacer(modifier = Modifier.height(spacing.medium))
                    }
                }
            }
        }

        if (uiState.isBottomSheetVisible){
            BottomSheetScreen(
                type = ActionType.TASK,
                onDismiss = {viewModel.hideBottomSheet()},
                onCreateClick = { title, time, repeatCount, category, priority ->
                    val newTask = UiTaskData(
                        title = title,
                        priority = priority,
                        category = category,
                        time = time ?: "",
                        isHabit = false,
                        targetCount = repeatCount,
                        description = "",
                        period = " ",
                        dayPart = DayPart.MORNING,
                        colorHex = 0,
                        isPinned = false,
                        iconResId = category.iconResId
                    )
                    viewModel.addTask(newTask)
                }
            )
        }
    }
}

