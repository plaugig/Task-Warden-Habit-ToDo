package com.example.taskwardenhabittodo.ui.components.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun SwipeBackground(swipeState: SwipeToDismissBoxState) {

    val progress = swipeState.progress

    val spacing = MaterialTheme.spacing

    val color = when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> WarningRed.copy(
            alpha = (progress * 1.5f).coerceAtMost(0.85f)
        )
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = color,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        if (swipeState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.35f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}