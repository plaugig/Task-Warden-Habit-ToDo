package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.presentation.item.DayProgress
import com.example.taskwardenhabittodo.presentation.item.UiUserData
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun DashboardStatsCard(
    user: UiUserData,
    progress: DayProgress,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val habitsProgress = progress.habitsProgress

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(spacing.extraLarge),
        color = colorScheme.surface,
        border = BorderStroke(1.dp, colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.medium)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // кольцо 1
                StatRing(
                    label = stringResource(R.string.stats_tasks_done),
                    subLabel = "",
                    value = "/${progress.totalHabits}",
                    target = "${progress.completedHabits}",
                    color = colorScheme.primary,
                    progress = habitsProgress
                )

                // кольцо 2
                StatRing(
                    label = stringResource(R.string.stats_mastery),
                    subLabel = stringResource(R.string.stats_days),
                    value = "${user.habitStreak}",
                    target = "",
                    color = colorScheme.tertiary,
                    progress = 1f
                )

                // кольцо 3
                StatRing(
                    label = stringResource(R.string.stats_fire_streak),
                    subLabel = stringResource(R.string.stats_days),
                    value = "${user.taskStreak}",
                    target = "",
                    color = MaterialTheme.extendedColors.fireStreak,
                    progress = 1f,
                    isFire = true
                )
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = colorScheme.outlineVariant.copy(alpha = 0.5f)
            )

            // всего поинтов
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(colorScheme.outline.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.coins),
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.small))
                    Column {
                        Text(
                            text = stringResource(R.string.stats_pet_points),
                            style = MaterialTheme.typography.labelSmall,
                            color = colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${user.petPoints}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold
                            ),
                            color = colorScheme.onSurface
                        )
                    }
                }

                // поинты ежед
                Surface(
                    color = colorScheme.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(spacing.medium)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.trending_up),
                            contentDescription = null,
                            tint = colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+${user.dailyPoints}",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatRing(
    label: String,
    subLabel: String,
    value: String,
    target: String,
    color: Color,
    progress: Float,
    isFire: Boolean = false
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.small)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(65.dp)) {
            Canvas(modifier = Modifier.size(65.dp)) {
                drawArc(
                    color = colorScheme.outline.copy(alpha = 0.3f),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                )

                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360f * progress,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (isFire) {
                    Icon(
                        painter = painterResource(id = R.drawable.local_fire),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = target,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = colorScheme.onSurface
                )
                if (value.isNotEmpty()) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.titleSmall,
                        color = colorScheme.onSurfaceVariant
                    )
                }

            }

        }

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun DashboardPreview() {
    TaskWardenHabitToDoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DashboardStatsCard(
                user = UiUserData(
                    petPoints = 12500,
                    dailyPoints = 150,
                    taskStreak = 12,
                    habitStreak = 5
                ),
                progress = DayProgress(
                    totalTasks = 10,
                    completedTasks = 7,
                    totalHabits = 5,
                    completedHabits = 3
                )
            )
        }
    }
}
