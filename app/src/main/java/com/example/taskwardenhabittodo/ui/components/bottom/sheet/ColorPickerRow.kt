package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorScheme.surfaceVariant.copy(alpha = 0.3f))
            .padding(horizontal = spacing.medium, vertical = spacing.small),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HabitColorPalette.forEach { color ->
            val isSelected = color == selectedColor

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color)
                    .then(
                        if (isSelected) Modifier.border(
                            width = 2.5.dp,
                            color = colorScheme.onSurface,
                            shape = RoundedCornerShape(10.dp)
                        ) else Modifier
                    )
                    .clickable { onColorSelected(color) }
            )
        }
    }
}