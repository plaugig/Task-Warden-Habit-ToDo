package com.example.taskwardenhabittodo.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskwardenhabittodo.presentation.archive.ArchiveDayScreen
import com.example.taskwardenhabittodo.presentation.archive.ArchiveDaysViewModel
import com.example.taskwardenhabittodo.presentation.cat.CatScreen
import com.example.taskwardenhabittodo.presentation.cat.CatViewModel
import com.example.taskwardenhabittodo.presentation.habit.HabitScreen
import com.example.taskwardenhabittodo.presentation.robot.RobotOverlay
import com.example.taskwardenhabittodo.presentation.robot.RobotOverlayViewModel
import com.example.taskwardenhabittodo.presentation.task.today.TasksScreen
import com.example.taskwardenhabittodo.presentation.tasks.history.HistoryDayTaskScreen

import com.example.taskwardenhabittodo.ui.components.TaskWardenBottomBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val robotViewModel: RobotOverlayViewModel = hiltViewModel()
    val robotState by robotViewModel.state.collectAsState()

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
        val focusManager = LocalFocusManager.current

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ){
            NavHost(
                navController = navController,
                startDestination = "home",
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
                    val catViewModel : CatViewModel = hiltViewModel()
                    val catState by catViewModel.uiState.collectAsState()

                    CatScreen(
                        uiState = catState,
                        onAction = catViewModel::onAction
                    )
                }
            }

            RobotOverlay(
                state = robotState,
                onDismiss = robotViewModel::dismiss
            )
        }
    }
}