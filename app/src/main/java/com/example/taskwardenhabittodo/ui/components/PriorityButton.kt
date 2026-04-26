package com.example.taskwardenhabittodo.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun PriorityButton(
    label: String,
    priorityColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing

    val backgroundColor = if (isSelected) priorityColor.copy(alpha = 0.15f)
    else
        Color.Transparent
    val borderColor = if (isSelected) priorityColor
    else
        MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
    val contentColor = if (isSelected) priorityColor
    else
        MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .height(40.dp)
            .animateContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        priorityColor,
                        androidx.compose.foundation.shape.CircleShape)

            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = label,
                color = contentColor,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )

            if (isSelected) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = priorityColor,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun PriorityButtonsPreview() {
    TaskWardenHabitToDoTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // выс
                PriorityButton(
                    label = "High",
                    priorityColor = Color(0xFFEF5350),
                    isSelected = true,
                    onClick = {}
                )

                // сред
                PriorityButton(
                    label = "Medium",
                    priorityColor = Color(0xFFFFB300),
                    isSelected = false,
                    onClick = {}
                )

                // низ
                PriorityButton(
                    label = "Low",
                    priorityColor = Color(0xFF66BB6A),
                    isSelected = false,
                    onClick = {}
                )
            }
        }
    }
}

