package com.example.taskwardenhabittodo.ui.components.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.HabitColorPurple
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun TaskCard(
    time: String?,
    period: String,
    title: String,
    priorityColor: Color? = null,
    isCompleted: Boolean = false,
    colorHex: Long = HabitColorPurple.value.toLong(),
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {}
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val accentColor = remember(colorHex) {
        try {
            Color(colorHex.toULong())
        } catch (e: Exception) {
            colorScheme.primary
        }
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        shape = RoundedCornerShape(32.dp),
        color = if (isCompleted)
            accentColor.copy(alpha = 0.04f)
        else
            accentColor.copy(alpha = 0.08f),
        border = BorderStroke(
            width = 1.5.dp,
            color = if (isCompleted)
                accentColor.copy(alpha = 0.2f)
            else
                accentColor.copy(alpha = 0.5f)
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
                    color = if (isCompleted)
                        accentColor.copy(alpha = 0.4f)
                    else
                        accentColor
                )
                Text(
                    text = period,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (isCompleted)
                        accentColor.copy(alpha = 0.3f)
                    else
                        accentColor.copy(alpha = 0.7f)
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = spacing.small)
                    .width(1.dp)
                    .height(30.dp)
                    .background(colorScheme.outline.copy(alpha = 0.3f))
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
                        textDecoration = if (isCompleted) TextDecoration.LineThrough
                        else
                            TextDecoration.None,
                        color = if (isCompleted) colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                        else
                            colorScheme.onSurface,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.width(spacing.small))

                    if (priorityColor != null) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(
                                    color = if (isCompleted)
                                        priorityColor.copy(alpha = 0.25f)
                                    else
                                        priorityColor,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 1.5.dp,
                                    color = if (isCompleted)
                                        priorityColor.copy(alpha = 0.15f)
                                    else
                                        priorityColor.copy(alpha = 0.4f),
                                    shape = CircleShape
                                )
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
                            .size(28.dp)
                            .background(color = accentColor.copy(alpha = 0.5f), CircleShape),
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
                                colorScheme.outline.copy(alpha = 0.75f),
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
                priorityColor = Color.Red,
                isCompleted = true,
                onClick = {}
            )

            TaskCard(
                time = "07:15",
                period = "AM",
                title = "Journaling",
                priorityColor = Color.Green,
                isCompleted = false,
                onClick = {}
            )
        }
    }
}



