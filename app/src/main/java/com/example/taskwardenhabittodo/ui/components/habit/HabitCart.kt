package com.example.taskwardenhabittodo.ui.components.habit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.domain.item.Priority
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HabitCard(
    habit: UiTaskData,
    onIncrement: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val isDone = habit.isCompleted

    val accentColor = remember(habit.colorHex) {
        try {
            Color(habit.colorHex)
        } catch (e: Exception) {
            colorScheme.primary
        }
    }

    val progress = if (habit.targetCount > 0) {
        habit.currentCount.toFloat() / habit.targetCount
    } else 0f

    Surface(
        modifier = modifier
            .combinedClickable(
                onClick = { if (!isDone) onIncrement() },
                onLongClick = onLongClick
            )
            .width(170.dp)
            .height(170.dp),
        shape = RoundedCornerShape(24.dp),
        color = if (isDone) accentColor.copy(alpha = 0.12f) else colorScheme.surface,
        border = BorderStroke(
            width = if (isDone) 2.dp else 1.5.dp,
            color = if (isDone) accentColor else colorScheme.outline.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.medium)
                .graphicsLayer(alpha = if (isDone) 0.9f else 1f),
            verticalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(accentColor.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = habit.iconResId),
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Surface(
                    color = accentColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.local_fire),
                            contentDescription = null,
                            tint = accentColor,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = stringResource(
                                R.string.fmt_streak_days,
                                habit.currentCount
                            ),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = accentColor
                        )
                    }
                }
            }

            Text(
                text = habit.title,
                fontSize = 18.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = colorScheme.onSurface,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 22.sp
            )

            LinearProgressIndicator(
                progress = { if (isDone) 1f else progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = accentColor,
                trackColor = colorScheme.outline.copy(alpha = 0.2f),
                strokeCap = StrokeCap.Round
            )

            if (isDone) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.common_done),
                        fontSize = 14.sp,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = accentColor
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(12.dp)
                    )
                }
            } else {
                Text(
                    text = stringResource(
                        R.string.fmt_habit_progress,
                        habit.currentCount,
                        habit.targetCount
                    ),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun HabitsStatusPreview() {
    TaskWardenHabitToDoTheme {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HabitCard(
                habit = UiTaskData(
                    id = 1,
                    title = "Drink Water",
                    targetCount = 5,
                    currentCount = 2,
                    isCompleted = true,
                    colorHex = 0xFF2196F3,
                    iconResId = R.drawable.workout,
                    priority = Priority.MEDIUM,
                    category = CategoryType.HEALTH,
                    time = "10:00",
                    period = "Daily",
                    dayPart = DayPart.MORNING,
                    isHabit = true,
                    description = "",
                    isPinned = false
                ),
                onIncrement = { },
                onLongClick = { },
                modifier = Modifier.weight(1f)
            )

            HabitCard(
                habit = UiTaskData(
                    id = 2,
                    title = "Read a Book",
                    targetCount = 1,
                    currentCount = 1,
                    isCompleted = false,
                    colorHex = 0xFFB39DDB,
                    iconResId = R.drawable.read,
                    priority = Priority.LOW,
                    category = CategoryType.WORK,
                    time = "21:00",
                    period = "Daily",
                    dayPart = DayPart.EVENING,
                    isHabit = true,
                    description = "",
                    isPinned = false
                ),
                onIncrement = { },
                onLongClick = { },
                modifier = Modifier.weight(1f)
            )
        }
    }
}