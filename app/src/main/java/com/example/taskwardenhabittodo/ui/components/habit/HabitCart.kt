package com.example.taskwardenhabittodo.ui.components.habit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.CategoryType
import com.example.taskwardenhabittodo.domain.DayPart
import com.example.taskwardenhabittodo.domain.Priority
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
                onClick = onIncrement,
                onLongClick = onLongClick
            )
            .width(160.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(28.dp),
        color = colorScheme.surface,
        border = BorderStroke(1.dp, colorScheme.outline.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(spacing.medium),
            verticalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
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
                    shape = RoundedCornerShape(12.dp)
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
                            text = stringResource(R.string.habit_streak_template, 7),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = accentColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.small))

            Text(
                text = habit.title,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.onSurface,
                maxLines = 2,
                minLines = 2
            )

            Spacer(modifier = Modifier.height(spacing.small))

            LinearProgressIndicator(
                progress = { if (habit.isCompleted) 1f else progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = accentColor,
                trackColor = colorScheme.outline.copy(alpha = 0.2f),
                strokeCap = StrokeCap.Round
            )

            if (habit.isCompleted) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.done),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
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
                        R.string.habit_progress_template,
                        habit.currentCount,
                        habit.targetCount
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun HabitsPreview() {
    TaskWardenHabitToDoTheme {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // вып
            HabitCard(
                habit = UiTaskData(
                    id = 1,
                    title = "Morning Workout",
                    description = "Gym session",
                    priority = Priority.HIGH,
                    category = CategoryType.SPORT,
                    iconResId = R.drawable.workout,
                    time = "08:00",
                    period = "Daily",
                    dayPart = DayPart.MORNING,
                    isCompleted = true,
                    isHabit = true,
                    targetCount = 1,
                    currentCount = 1,
                    colorHex = 0xFF00C853,
                    isPinned = false
                ),
                onIncrement = { },
                onLongClick = { },
                modifier = Modifier.weight(1f)
            )

            HabitCard(
                habit = UiTaskData(
                    id = 2,
                    title = "Read 20 Pages",
                    description = "Read a book",
                    priority = Priority.MEDIUM,
                    category = CategoryType.WORK,
                    iconResId = R.drawable.read,
                    time = "20:00",
                    period = "Daily",
                    dayPart = DayPart.EVENING,
                    isCompleted = false,
                    isHabit = true,
                    targetCount = 20,
                    currentCount = 11,
                    colorHex = 0xFFB39DDB,
                    isPinned = false
                ),
                onIncrement = {},
                onLongClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}
