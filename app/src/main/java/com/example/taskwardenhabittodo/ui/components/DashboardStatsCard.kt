package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.UiUserData
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun DashboardStatsCard(
    user: UiUserData,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val tasksProgress = if (user.totalTasks > 0) user.completedTasks.toFloat() /
            user.totalTasks
    else 0f
    val masteredProgress = if (user.targetMastery > 0) user.masteredCount.toFloat() /
            user.targetMastery
    else 0f

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(spacing.extraLarge),
        color = colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(
            1.dp, colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier
                .padding(spacing.medium)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            // статистик на кольцах
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatRing(
                    label = stringResource(R.string.tasks_done),
                    subLabel = stringResource(
                        R.string.tasks_complete_template,
                        (tasksProgress * 100).toInt()
                    ),
                    value = "${user.completedTasks}",
                    target = "/${user.totalTasks}",
                    color = colorScheme.tertiary,
                    progress = tasksProgress
                )

                StatRing(
                    label = stringResource(R.string.Mastered),
                    subLabel = stringResource(
                        R.string.mastered_done_template,
                        (masteredProgress * 100).toInt()
                    ),
                    value = "${user.masteredCount}",
                    target = "/${user.targetMastery}",
                    color = colorScheme.primary,
                    progress = masteredProgress
                )

                StatRing(
                    label = stringResource(R.string.fire_streak),
                    subLabel = stringResource(R.string.days),
                    value = "${user.fireStreak}",
                    target = "",
                    color = MaterialTheme.extendedColors.fireStreak,
                    progress = 1f,
                    isFire = true
                )
            }

            // разделитель
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = colorScheme.outlineVariant.copy(alpha = 0.5f)
            )

            // нижний блок
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // общ колво поинтов
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(colorScheme.outline, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.coins),
                            contentDescription = null,
                            tint = colorScheme.primary,
                            modifier = Modifier.size(spacing.large)
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.small))
                    Column {
                        Text(
                            text = stringResource(R.string.pet_points),
                            style = MaterialTheme.typography.labelSmall,
                            color = colorScheme.onSurfaceVariant,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = stringResource(R.string.pet_points_format, user.petPoints),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold
                            ),
                            color = colorScheme.onSurface
                        )
                    }
                }

                // правая часть
                Surface(
                    color = colorScheme.secondary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(spacing.medium)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = spacing.small, vertical = spacing.extraSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.trending_up),
                            contentDescription = null,
                            tint = colorScheme.tertiary,
                            modifier = Modifier.size(spacing.medium)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(
                                R.string.today_points_template,
                                user.dailyPoints
                            ),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorScheme.tertiary
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
                    color = colorScheme.outline,
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
                        painter = painterResource(
                            id = R.drawable.local_fire
                        ),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(spacing.medium)
                    )
                }
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = colorScheme.onSurface
                )
                if (target.isNotEmpty()) {
                    Text(
                        text = target,
                        style = MaterialTheme.typography.labelSmall,
                        color = colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = colorScheme.onSurface
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(6.dp).background(color, CircleShape))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = subLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun DashboardPreview() {
    TaskWardenHabitToDoTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            DashboardStatsCard(
                user = UiUserData(
                    id = 0,
                    petPoints = 1250050,
                    dailyPoints = 150,
                    fireStreak = 12,
                    masteredCount = 3,
                    totalTasks = 8,
                    completedTasks = 5,
                    targetMastery = 5
                )
            )
        }
    }
}

