package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.HabitColorPalette
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun ColorPickerRow(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val spacing = MaterialTheme.spacing

    Text(
        text = stringResource(R.string.bottom_sheet_color),
        style = MaterialTheme.typography.labelMedium,
        color = colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.small)
    ) {
        HabitColorPalette.forEach { color ->
            val isSelected = color == selectedColor

            val backgroundColor = if (isSelected)
                color.copy(alpha = 0.15f)
            else
                Color.Transparent

            val borderColor = if (isSelected)
                color
            else
                colorScheme.outline.copy(alpha = 0.2f)

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor)
                    .border(1.dp, borderColor, RoundedCornerShape(12.dp))
                    .clickable { onColorSelected(color) },
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        painter = painterResource(R.drawable.check),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(color, CircleShape)
                    )
                }
            }
        }
    }
}