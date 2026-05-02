package com.example.taskwardenhabittodo.ui.presentation.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.CategoryType
import com.example.taskwardenhabittodo.domain.DayPart
import com.example.taskwardenhabittodo.domain.Priority
import com.example.taskwardenhabittodo.ui.DayProgress
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.UiUserData
import com.example.taskwardenhabittodo.ui.components.DashboardStatsCard
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.components.MainScreenDashboard
import com.example.taskwardenhabittodo.ui.components.habit.HabitsFlowGrid
import com.example.taskwardenhabittodo.ui.components.habit.HabitsSectionHeader
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

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
                    title = stringResource(id = R.string.todays_focus),
                    completedCount = uiState.progress.completedTasks,
                    totalCount = uiState.progress.totalTasks,
                    progress = uiState.progress.tasksProgress,
                    isFullMode = true,
                    modifier = Modifier.padding(vertical = spacing.small)
                )
            }

            item {
                HabitsSectionHeader()
            }

            item {
                HabitsFlowGrid(
                    habits = uiState.todayHabits,
                    onIncrement = { habitId, currentCount ->
                        viewModel.updateHabitProgress(habitId, currentCount + 1)
                    },
                    onDelete = { id ->
                        viewModel.deleteHabitById(id)
                    }
                )
            }
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
            fireStreak = 7,
            masteryStreak = 3
        )

        val mockHabits = listOf(
            UiTaskData(
                id = 1,
                title = "Drinking Water",
                description = "Stay hydrated",
                priority = Priority.MEDIUM,
                category = CategoryType.HEALTH,
                iconResId = R.drawable.meditate,
                time = "09:00",
                period = "Daily",
                dayPart = DayPart.MORNING,
                isCompleted = false,
                targetCount = 8,
                currentCount = 3,
                colorHex = 0xFF2196F3,
                isPinned = true
            ),
            UiTaskData(
                id = 2,
                title = "Workout",
                description = "Morning gym",
                priority = Priority.HIGH,
                category = CategoryType.SPORT,
                iconResId = R.drawable.workout,
                time = "07:30",
                period = "Daily",
                dayPart = DayPart.MORNING,
                isCompleted = true,
                targetCount = 1,
                currentCount = 1,
                colorHex = 0xFF4CAF50,
                isPinned = false
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
                item {
                    MainScreenDashboard()
                }

                item {
                    DashboardStatsCard(
                        user = mockUser,
                        progress = mockProgress
                    )

                    Spacer(modifier = Modifier.height(spacing.small))

                    FocusTaskCard(
                        title = stringResource(id = R.string.todays_focus),
                        completedCount = mockProgress.completedTasks,
                        totalCount = mockProgress.totalTasks,
                        progress = mockProgress.tasksProgress,
                        isFullMode = true
                    )
                }



                item {
                    HabitsSectionHeader()
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
