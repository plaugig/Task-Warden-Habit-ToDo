package com.example.taskwardenhabittodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.taskwardenhabittodo.presentation.MainScreen
import com.example.taskwardenhabittodo.presentation.habit.HabitScreen
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TaskWardenHabitToDoTheme {
                MainScreen()
            }
        }
    }
}
