package com.example.taskwardenhabittodo.presentation.habit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.ActionType
import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.presentation.item.DayProgress
import com.example.taskwardenhabittodo.presentation.item.UiHabitData
import com.example.taskwardenhabittodo.presentation.item.UiUserData
import com.example.taskwardenhabittodo.ui.components.ActionIconButton
import com.example.taskwardenhabittodo.ui.components.bottom.sheet.BottomSheetScreen
import com.example.taskwardenhabittodo.ui.components.DashboardStatsCard
import com.example.taskwardenhabittodo.ui.components.DeleteHabitDialog
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.components.MainScreenDashboard
import com.example.taskwardenhabittodo.ui.components.habit.HabitsFlowGrid
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun HabitScreen(
    viewModel: HabitScreenViewModel = hiltViewModel(),
    onNavigateToTasks: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    var showBottomSheet by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var habitIdToDelete by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorScheme.background
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = spacing.medium),
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            item {
                MainScreenDashboard()
            }

            item {
                DashboardStatsCard(
                    user = uiState.user,
                    progress = uiState.progress
                )

                Spacer(modifier = Modifier.height(spacing.small))

                FocusTaskCard(
                    title = stringResource(id = R.string.stats_todays_focus),
                    completedCount = uiState.progress.completedTasks,
                    totalCount = uiState.progress.totalTasks,
                    progress = uiState.progress.tasksProgress,
                    isFullMode = true,
                    modifier = Modifier
                        .padding(vertical = spacing.small)
                        .clickable { onNavigateToTasks() }
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.task_title_header),
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    ActionIconButton(
                        iconRes = R.drawable.add,
                        onClick = { showBottomSheet = true },
                        isSelected = true
                    )
                }
            }

            item {
                HabitsFlowGrid(
                    habits = uiState.todayHabits,
                    onIncrement = { habitId, currentCount ->
                        viewModel.updateHabitProgress(habitId, currentCount)
                    },
                    onDelete = { id ->
                        habitIdToDelete = id
                        showDeleteDialog = true
                    }
                )
            }
        }

        if (showDeleteDialog) {
            DeleteHabitDialog(
                onConfirm = {
                    habitIdToDelete?.let { viewModel.deleteHabitById(it) }
                    showDeleteDialog = false
                    habitIdToDelete = null
                },
                onDismiss = {
                    showDeleteDialog = false
                    habitIdToDelete = null
                }
            )
        }

        if (showBottomSheet) {
            BottomSheetScreen(
                type = ActionType.HABIT,
                onDismiss = { showBottomSheet = false },
                onCreateHabit = { input ->
                    val newHabit = UiHabitData(
                        title = input.title,
                        description = "",
                        time = input.time,
                        targetCount = input.repeatCount,
                        currentCount = 0,
                        category = input.category,
                        iconResId = input.category.iconResId,
                        isCompleted = false,
                        colorHex = input.colorHex
                    )
                    viewModel.addHabit(newHabit)
                    showBottomSheet = false
                }
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun MainScreenPreview() {
    TaskWardenHabitToDoTheme {
        val spacing = MaterialTheme.spacing

        val mockUser = UiUserData(
            petPoints = 150,
            dailyPoints = 45,
            taskStreak = 7,
            habitStreak = 3
        )

        val mockHabits = listOf(
            UiHabitData(
                id = 1,
                title = "Drinking Water",
                description = "Stay hydrated",
                category = CategoryType.HEALTH,
                iconResId = R.drawable.meditate,
                time = "09:00",
                isCompleted = false,
                targetCount = 8,
                currentCount = 3,
                colorHex = 0xFF2196F3
            ),
            UiHabitData(
                id = 2,
                title = "Workout",
                description = "Morning gym",
                category = CategoryType.SPORT,
                iconResId = R.drawable.workout,
                time = "07:30",
                isCompleted = true,
                targetCount = 1,
                currentCount = 1,
                colorHex = 0xFF4CAF50
            )
        )

        val mockProgress = DayProgress(
            totalTasks = 5,
            completedTasks = 2,
            totalHabits = 2,
            completedHabits = 1
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = spacing.medium),
                verticalArrangement = Arrangement.spacedBy(spacing.large)
            ) {
                item { MainScreenDashboard() }

                item {
                    DashboardStatsCard(user = mockUser, progress = mockProgress)
                    Spacer(modifier = Modifier.height(spacing.small))
                    FocusTaskCard(
                        title = stringResource(id = R.string.stats_todays_focus),
                        completedCount = mockProgress.completedTasks,
                        totalCount = mockProgress.totalTasks,
                        progress = mockProgress.tasksProgress,
                        isFullMode = true
                    )
                }

                item {
                    HabitsFlowGrid(
                        habits = mockHabits,
                        onIncrement = { _, _ -> },
                        onDelete = {}
                    )
                }
            }
        }
    }
}
