package com.example.taskwardenhabittodo.ui.theme

import android.icu.util.Calendar
import androidx.compose.runtime.Composable

@Composable
fun getCurrentFormattedDate(): String {
    val calendar = Calendar.getInstance()
    val formatter = java.text.SimpleDateFormat(
        "EEEE, d MMMM",
        java.util.Locale.getDefault()
    )
    return formatter.format(calendar.time)
}