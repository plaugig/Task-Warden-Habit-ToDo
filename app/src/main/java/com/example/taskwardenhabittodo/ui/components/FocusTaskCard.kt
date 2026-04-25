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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun FocusTaskCard(
    title: String,
    subTitle: String,
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isFullMode) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    colorScheme.tertiary.copy(alpha = 0.1f),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.focus
                                ),
                                contentDescription = null,
                                tint = colorScheme.tertiary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(spacing.small))
                    }

                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = colorScheme.onSurface
                        )
                        Text(
                            text = subTitle,
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (isFullMode) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(
                                R.string.focus_percentage_template,
                                (progress * 100).toInt()
                            ),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold
                            ),
                            color = colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(spacing.small))

                        Surface(
                            modifier = Modifier.size(32.dp),
                            shape = CircleShape,
                            color = colorScheme.outline.copy(alpha = 0.2f)
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.arrow_right
                                ),
                                contentDescription = null,
                                tint = colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                } else {
                    Surface(
                        color = colorScheme.secondary.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(spacing.small)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = spacing.small,
                                vertical = spacing.extraSmall
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.local_fire
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.extendedColors.fireStreak,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(spacing.extraSmall))
                            Text(
                                text = stringResource(
                                    R.string.streak_days_template,
                                    streak
                                ),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.extendedColors.fireStreak
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.small))

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = colorScheme.tertiary,
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            FocusTaskCard(
                title = "Today's Focus",
                subTitle = "Morning Workout · 30 min left",
                progress = 0.65f,
                isFullMode = true
            )
            FocusTaskCard(
                title = "Daily Progress",
                subTitle = "4 / 9 done",
                progress = 0.44f,
                isFullMode = false,
                streak = 12
            )
        }
    }
}