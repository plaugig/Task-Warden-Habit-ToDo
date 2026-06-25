package com.example.taskwardenhabittodo.ui.components.cat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiData

@Composable
fun CatStatsCard(
    stats: List<CatUiData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(

                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        stats.forEachIndexed { index, stat ->
            StatRow(statData = stat)

            if (index < stats.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                )
            }
        }
    }
}

@Composable
private fun StatRow(
    statData: CatUiData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(color = statData.iconBgColor, shape = RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = statData.iconRes),
                contentDescription = statData.title,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = statData.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = statData.statusLabel,
                        color = statData.accentColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = " · ${(statData.progress * 100).toInt()}/100",
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { statData.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = statData.accentColor,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatStatsCardPreview() {
    com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            val mockStats = listOf(
                CatUiData(
                    title = "Mood",
                    statusLabel = "Happy",
                    progress = 0.82f,
                    iconRes = com.example.taskwardenhabittodo.R.drawable.health,
                    accentColor = Color(0xFFB09FFF),
                    iconBgColor = Color(0xFF2D293A)
                ),
                CatUiData(
                    title = "Fullness",
                    statusLabel = "Well Fed",
                    progress = 0.65f,
                    iconRes = com.example.taskwardenhabittodo.R.drawable.history,
                    accentColor = Color(0xFF2DD4BF),
                    iconBgColor = Color(0xFF1F3532)
                ),
                CatUiData(
                    title = "Energy",
                    statusLabel = "Energetic",
                    progress = 0.45f,
                    iconRes = com.example.taskwardenhabittodo.R.drawable.animal,
                    accentColor = Color(0xFFFBBF24),
                    iconBgColor = Color(0xFF383220)
                )
            )

            CatStatsCard(stats = mockStats)
        }
    }
}