package com.example.taskwardenhabittodo.ui.components.cat

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CatActionButton(
    title: String,
    subtitle: String,
    @DrawableRes iconRes: Int,
    accentColor: Color,
    iconBgColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .background(color = iconBgColor, shape = RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))


        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Text(
                text = subtitle,
                color = accentColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatActionHorizontalGridPreview() {
    com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            CatActionButton(
                title = "Feed",
                subtitle = "+25 Fullness",
                iconRes = com.example.taskwardenhabittodo.R.drawable.focus,
                accentColor = Color(0xFF2DD4BF),
                iconBgColor = Color(0xFF1F3532),
                onClick = {},
                modifier = Modifier.weight(1f)
            )


            CatActionButton(
                title = "Play",
                subtitle = "+30 Mood",
                iconRes = com.example.taskwardenhabittodo.R.drawable.animal,
                accentColor = Color(0xFFB09FFF),
                iconBgColor = Color(0xFF2D293A),
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}