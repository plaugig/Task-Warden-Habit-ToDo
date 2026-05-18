package com.example.taskwardenhabittodo.ui.components.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun TaskCard(
    time: String?,
    period: String,
    title: String,
    duration: Int,
    priorityColor: Color? = null,
    isCompleted: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val containerColor = if (isCompleted) {
        colorScheme.secondary.copy(alpha = 0.1f)
    } else {
        colorScheme.surface
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(32.dp),
        color = containerColor,
        border = if (isCompleted) null else BorderStroke(
            1.dp,
            colorScheme.outline.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(50.dp)
            ) {
                Text(
                    text = time ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    ),
                    color = if (isCompleted) colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    else colorScheme.onSurface
                )
                Text(
                    text = period,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (isCompleted) colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                    else colorScheme.onSurfaceVariant
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = spacing.small)
                    .width(1.dp)
                    .height(30.dp)
                    .background(colorScheme.outline.copy(alpha = 0.2f))
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = spacing.small)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (isCompleted) colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        else colorScheme.onSurface,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(spacing.small))

                    if (priorityColor != null) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(priorityColor, CircleShape)
                        )
                    }
                }
            }

            IconButton(
                onClick = { onCheckedChange(!isCompleted) },
                modifier = Modifier.size(40.dp)
            ) {
                if (isCompleted) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF00C853), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .border(
                                2.dp,
                                colorScheme.outline.copy(alpha = 0.5f),
                                CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun TaskCardPreview() {
    TaskWardenHabitToDoTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TaskCard(
                time = "09:00",
                period = "AM",
                title = "Team Standup",
                duration = 15,
                priorityColor = Color.Red,
                isCompleted = true,
                onClick = {}
            )

            TaskCard(
                time = "07:15",
                period = "AM",
                title = "Journaling",
                duration = 10,
                priorityColor = Color.Green,
                isCompleted = false,
                onClick = {}
            )
        }
    }
}



