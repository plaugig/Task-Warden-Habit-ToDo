package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun ActionIconButton(
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    actionColor: Color = MaterialTheme.colorScheme.primary,
    containerSize: Dp = 48.dp
) {

    val colorScheme = MaterialTheme.colorScheme


    val currentContainerColor = if (isSelected) {
        actionColor.copy(alpha = 0.15f)
    } else {
        Color.Transparent
    }

    val currentContentColor = if (isSelected) {
        actionColor
    } else {
        colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
    }

    Box(
        modifier = modifier
            .size(containerSize)
            .clip(RoundedCornerShape(16.dp))
            .background(currentContainerColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = currentContentColor,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun ActionButtonsPreview() {
    val colorScheme = MaterialTheme.colorScheme

    ActionIconButton(
        iconRes = R.drawable.animal,
        onClick = {},
    )
}