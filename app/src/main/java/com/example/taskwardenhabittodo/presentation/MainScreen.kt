package com.example.taskwardenhabittodo.presentation

import android.R.attr.type
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.presentation.archive.ArchiveDayScreen
import com.example.taskwardenhabittodo.presentation.archive.ArchiveDaysViewModel
import com.example.taskwardenhabittodo.presentation.cat.CatScreen
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiData
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiState
import com.example.taskwardenhabittodo.presentation.habit.HabitScreen
import com.example.taskwardenhabittodo.presentation.task.today.TasksScreen
import com.example.taskwardenhabittodo.presentation.tasks.history.HistoryDayTaskScreen

import com.example.taskwardenhabittodo.ui.components.TaskWardenBottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == "home" || currentRoute == "tasks" || currentRoute == "lair") {
                TaskWardenBottomBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HabitScreen(
                    onNavigateToTasks = {
                        navController.navigate("tasks"){
                            popUpTo("home") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable("tasks") {
                TasksScreen(
                    onNavigateToArchive = {
                        navController.navigate("archive")
                    }
                )
            }

            composable("archive") {
                val archiveViewModel: ArchiveDaysViewModel = hiltViewModel()
                ArchiveDayScreen(
                    viewModel = archiveViewModel,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onDayClick = { timestamp ->
                        navController.navigate("history_day_tasks/$timestamp")
                    }
                )
            }

            composable(
                route = "history_day_tasks/{timestamp}",
                arguments = listOf(navArgument("timestamp") { type = NavType.LongType })
            ) { backStackEntry ->
                val timestamp = backStackEntry.arguments?.getLong("timestamp") ?: 0L

                HistoryDayTaskScreen(
                    timestamp = timestamp,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable("lair") {
                val fakeCatState = CatUiState(
                    backgroundRes = R.drawable.room4,
                    catImageRes = R.drawable.the_cat_is_afraid,
                    stats = listOf(
                        CatUiData(
                            title = "Mood",
                            statusLabel = "Happy",
                            progress = 0.82f,
                            iconRes = R.drawable.mood_heart,
                            accentColor = Color(0xFFB09FFF),
                            iconBgColor = Color(0xFF2D293A)
                        ),
                        CatUiData(
                            title = "Fullness",
                            statusLabel = "Well Fed",
                            progress = 0.65f,
                            iconRes = R.drawable.food,
                            accentColor = Color(0xFF2DD4BF),
                            iconBgColor = Color(0xFF1F3532)
                        )
                    )
                )
                CatScreen(
                    uiState = fakeCatState,
                    onFeedClick = {
                    },
                    onPlayClick = {
                    }
                )
            }
        }
    }
}