package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun FocusTaskCard(
    title: String,
    completedCount: Int,
    totalCount: Int,
    progress: Float,
    isFullMode: Boolean = true,
    streak: Int = 0,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = colorScheme.surface,
        border = BorderStroke(1.dp, colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(spacing.medium)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isFullMode) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(
                                colorScheme.primary.copy(alpha = 0.15f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.focus),
                            contentDescription = null,
                            tint = colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(spacing.medium))
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.sp
                        )
                    )
                    Text(
                        text = stringResource(
                            id = R.string.habit_progress_template,
                            completedCount,
                            totalCount
                        ) + " " + stringResource(id = R.string.done)
                            .replace("%", ""),
                        style = MaterialTheme.typography.labelMedium,
                        color = colorScheme.onSurfaceVariant
                    )
                }

                if (isFullMode) {
                    Text(
                        text = stringResource(
                            id = R.string.focus_percentage_template,
                            (progress * 100).toInt()
                        ),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        color = colorScheme.primary
                    )
                } else if (streak > 0) {
                    Surface(
                        color = MaterialTheme.extendedColors.fireStreak,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.local_fire),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(
                                    id = R.string.habit_streak_template,
                                    streak
                                ),
                                color = Color.White,
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.medium))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(CircleShape),
                color = colorScheme.primary,
                trackColor = colorScheme.outline.copy(alpha = 0.2f),
                strokeCap = StrokeCap.Round
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun FocusTaskPreview() {
    TaskWardenHabitToDoTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // фокус
            FocusTaskCard(
                title = "Today's Focus",
                completedCount = 4,
                totalCount = 6,
                progress = 0.65f,
                isFullMode = true
            )

            //прогресс
            FocusTaskCard(
                title = "Daily Progress",
                completedCount = 4,
                totalCount = 9,
                progress = 0.44f,
                isFullMode = false,
                streak = 12
            )
        }
    }
}

